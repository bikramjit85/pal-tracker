package io.pivotal.pal.tracker;

import com.google.gson.Gson;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    @Qualifier("JdbcTimeEntryRepository")
    private TimeEntryRepository timeEntryRepository;


    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(@Qualifier("JdbcTimeEntryRepository") TimeEntryRepository timeEntryRepository,
                               @Autowired MeterRegistry meterRegistry ) {
        this.timeEntryRepository = timeEntryRepository;


        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");

    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody  TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());

        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        HttpStatus httpStatus = HttpStatus.OK;
        if (timeEntry == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        actionCounter.increment();

        return new ResponseEntity<>(timeEntry, httpStatus);
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        actionCounter.increment();

        List<TimeEntry> timeEntry = timeEntryRepository.list();

        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable  long id, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(id, expected);

        HttpStatus httpStatus = HttpStatus.OK;
        if (timeEntry == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        actionCounter.increment();

        return new ResponseEntity<>(timeEntry, httpStatus);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable  long id) {

        timeEntryRepository.delete(id);

        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);

    }
}
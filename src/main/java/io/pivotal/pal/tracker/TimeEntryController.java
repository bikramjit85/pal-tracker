package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {

    @Autowired
    @Qualifier("InMemoryTimeEntryRepository")
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(@Qualifier("InMemoryTimeEntryRepository") TimeEntryRepository timeEntryRepository ) {
        this.timeEntryRepository = timeEntryRepository;

    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);


        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        HttpStatus httpStatus = HttpStatus.OK;
        if (timeEntry == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(timeEntry, httpStatus);
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {


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

        return new ResponseEntity<>(timeEntry, httpStatus);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable  long id) {

        timeEntryRepository.delete(id);

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);

    }
}

package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("InMemoryTimeEntryRepository")
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long id = 0L;
    public long getId() {

        id = id + 1;
        return id;
    }
    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();

    public InMemoryTimeEntryRepository() {
        id = 0L;
    }
    public TimeEntry create(TimeEntry timeEntry) {
        long key = getId();
        timeEntry.setId(key);
        System.out.println("key > " + key);
        timeEntryMap.put(timeEntry.getId(), timeEntry);
        return timeEntryMap.get(timeEntry.getId());
    }

    public TimeEntry find(Long timeEntryId) {

        return  timeEntryMap.get(timeEntryId);
    }

    public List<TimeEntry> list() {

        return new ArrayList<>(timeEntryMap.values());
    }

    public TimeEntry update(Long l, TimeEntry timeEntry) {

        TimeEntry timeEntryDb = timeEntryMap.get(l);
        if (timeEntryDb == null) {
            return null;
        }
        timeEntry.setId(l);
        timeEntryDb.setId(l);
        timeEntryDb.setDate(timeEntry.getDate());
        timeEntryDb.setProjectId(timeEntry.getProjectId());
        timeEntryDb.setHours(timeEntry.getHours());

        timeEntryDb.setUserId(timeEntry.getUserId());
        return timeEntryMap.put(l, timeEntryDb);
    }

    public void delete(Long id) {
        timeEntryMap.remove(id);
    }
}
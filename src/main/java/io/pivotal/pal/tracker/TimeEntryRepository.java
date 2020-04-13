package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry timeEntryToCreate);

    TimeEntry find(Long timeEntryId);

    List<TimeEntry> list();

    TimeEntry update(Long timeEntryId, TimeEntry expected);

    void delete(Long timeEntryId);
}

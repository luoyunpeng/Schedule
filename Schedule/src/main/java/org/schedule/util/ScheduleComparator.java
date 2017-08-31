package org.schedule.util;

import java.util.Comparator;

import org.schedule.entity.Schedule;

public class ScheduleComparator implements Comparator<Schedule> {

    @Override
    public int compare(Schedule s1, Schedule s2) {

        // id
        int tmp = s1.getPeriodId() - s2.getPeriodId();
        return tmp;
    }
}

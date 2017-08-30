package org.schedule.service;

import java.sql.Timestamp;
import java.util.List;

import org.schedule.dao.ScheduleDao;
import org.schedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class SchduleService {

    @Autowired
    ScheduleDao scheduleDao;

    public int loadSchedule(List<Schedule> list) {
        scheduleDao.loadSchedule(list);
        return 0;
    }

    public List<Schedule> getScheduleByName(String name) {

        return scheduleDao.getScheduleByName(name);
    }

    public List<Schedule> getScheduleByJobNumber(String jobNumber) {

        return scheduleDao.getScheduleByJobNumber(jobNumber);
    }

    public List<Schedule> getScheduleByDepartment(String department) {

        return scheduleDao.getScheduleByDepartment(department);
    }

    public List<Schedule> getScheduleByContent(String content) {

        return scheduleDao.getScheduleByContent(content);
    }

    public List<Schedule> getScheduleByTime(Timestamp startTime, Timestamp endTime) {

        return scheduleDao.getScheduleByTime(startTime, endTime);
    }

}

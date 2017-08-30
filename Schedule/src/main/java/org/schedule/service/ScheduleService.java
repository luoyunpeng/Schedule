package org.schedule.service;

import java.util.Date;
import java.util.List;

import org.schedule.dao.ScheduleDao;
import org.schedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;

    public int loadSchedule(List<Schedule> list) {
        scheduleDao.loadSchedule(list);
        return 0;
    }

    public List<Schedule> getScheduleByName(String name) {

        return scheduleDao.getScheduleByName(name);
    }
    
    public List<Schedule> getScheduleByNameAndTime(String name,Date startDate, Date endDate) {

        return scheduleDao.getScheduleByNameAndTime(name, startDate, endDate);
    }


    public List<Schedule> getScheduleByJobNumber(String jobNumber) {

        return scheduleDao.getScheduleByJobNumber(jobNumber);
    }

    public List<Schedule> getScheduleByContent(String content) {

        return scheduleDao.getScheduleByContent(content);
    }

    public List<Schedule> getScheduleByTime(Date startDate, Date endDate) {

        return scheduleDao.getScheduleByTime(startDate, endDate);
    }

}

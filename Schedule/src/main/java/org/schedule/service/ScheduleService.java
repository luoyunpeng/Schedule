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

        return scheduleDao.loadSchedule(list);
    }

    public List<Schedule> getScheduleByName(String name) {

        return scheduleDao.getScheduleByName(name);
    }

    public List<Schedule> getScheduleByNameAndDate(String name, Date startDate, Date endDate) {

        return scheduleDao.getScheduleByNameAndDate(name, startDate, endDate);
    }
    
    public List<Schedule> getScheduleByNameAndDateWithTimeSolt(String name, String timeSolt, Date startDate, Date endDate) {
        
        return scheduleDao.getScheduleByNameAndDateWithTimeSolt(name, timeSolt, startDate, endDate);
    }
    
    public List<Schedule> getScheduleByDate(Date startDate, Date endDate) {

        return scheduleDao.getScheduleByDate(startDate, endDate);
    }

    public List<Schedule> getScheduleByContent(String content) {
        content = "%" + content + "%";
        return scheduleDao.getScheduleByContent(content);
    }

    public List<Schedule> getScheduleByContentAndDate(String content, Date startDate, Date endDate) {
        content = "%" + content + "%";
        return scheduleDao.getScheduleByContentAndDate(content, startDate, endDate);
    }
    
    public List<Schedule> getScheduleByContentAndDateWithTimeSolt(String content, String timeSolt, Date startDate, Date endDate) {
        content = "%" + content + "%";
        return scheduleDao.getScheduleByContentAndDateWithTimeSolt(content, timeSolt, startDate, endDate);
    }

    public List<Schedule> getScheduleByTimeSolt(String timeSolt) {

        return scheduleDao.getScheduleByTimeSolt(timeSolt);
    }

    public List<Schedule> getScheduleByTimeSoltAndDate(String timeSolt, Date startDate, Date endDate) {

        return scheduleDao.getScheduleByTimeSoltAndDate(timeSolt, startDate, endDate);
    }
}
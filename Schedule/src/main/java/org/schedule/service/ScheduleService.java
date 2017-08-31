package org.schedule.service;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.schedule.dao.ScheduleDao;
import org.schedule.entity.Schedule;
import org.schedule.util.ScheduleComparator;
import org.schedule.util.WeekUtil;
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

    public List<Schedule> getScheduleByNameAndDate(String name, String currentDate) throws ParseException {
        Date current = WeekUtil.parse(currentDate);
        int dayNumber = WeekUtil.getWeekNumber(current);
        
        return scheduleDao.getScheduleByNameAndDate(name, WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber));
    }
    
    public List<Schedule> getScheduleByNameAndDateWithTimeSolt(String name, String timeSolt, String currentDate) throws ParseException {
        Date current = WeekUtil.parse(currentDate);
        int dayNumber = WeekUtil.getWeekNumber(current);
        
        return scheduleDao.getScheduleByNameAndDateWithTimeSolt(name, timeSolt, WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber));
    }
    
    public List<Schedule> getScheduleByDate(String currentDate) throws ParseException {
        Date current = WeekUtil.parse(currentDate);
        int dayNumber = WeekUtil.getWeekNumber(current);
        
        List<Schedule> list= scheduleDao.getScheduleByDate(WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber));
        
        for(Schedule s : list) {
            System.out.println(s.toString());
        }
        
        System.out.println("#######################");
        Collections.sort(list, new ScheduleComparator());
        
        for(Schedule s : list) {
            System.out.println(s.toString());
        }
        return list;
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
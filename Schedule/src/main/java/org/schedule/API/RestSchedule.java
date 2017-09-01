package org.schedule.API;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.schedule.entity.Level;
import org.schedule.entity.Schedule;
import org.schedule.entity.Week;
import org.schedule.service.ScheduleService;
import org.schedule.util.WeekUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RestSchedule {
	
    @Autowired
    ScheduleService scheduleService;
	
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public List<Schedule> getScheduleByName(@RequestParam(value = "name", required = true) String name) {
        
        return scheduleService.getScheduleByName(name);
    }
    
    @RequestMapping(value = "/leader", method = RequestMethod.GET)
    public List<Level> getLeaderName() {
        
        return scheduleService.getAllLeaderName();
    }
    
    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public Week getCurrentWeekSEDate() throws ParseException {
        String currentDate =  WeekUtil.format(new Date());
       
        Date current = WeekUtil.parse(currentDate);
        int dayNumber = WeekUtil.getWeekNumber(current);
        return new Week(WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber));
    }
	
    @RequestMapping(value = "/schedule/name/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByNameAndDate(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "currentDate", required = true) String currentDate) throws ParseException {
        
        return scheduleService.getScheduleByNameAndDate(name, currentDate);
    }
	
    @RequestMapping(value = "/schedule/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByDate(@RequestParam(value = "currentDate", required = true) String currentDate) throws ParseException {
        
        return scheduleService.getScheduleByDate(currentDate);
    }
    
    //@RequestMapping(value = "/schedule/name/date/timesolt", method = RequestMethod.GET)
    public List<Schedule> getScheduleByNameAndDateWithTimeSolt(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "timeSolt", required = true) String timeSolt, @RequestParam(value = "currentDate", required = true) String currentDate) throws ParseException {
        
        return scheduleService.getScheduleByNameAndDateWithTimeSolt(name, timeSolt, currentDate);
    }

    @RequestMapping(value = "/schedule/content/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByContend(@RequestParam(value = "content", required = true) String content, @RequestParam(value = "currentDate", required = true) String currentDate) throws ParseException {
        
        return scheduleService.getScheduleByContent(content, currentDate);
    }
    
    // @RequestMapping(value = "/schedule/content/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByContendAndDate(@RequestParam(value = "content", required = true) String content,@RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
        Date start = WeekUtil.parse(startDate);
        Date end  = WeekUtil.parse(endDate);
        return scheduleService.getScheduleByContentAndDate(content, start, end);
    }
    
    // @RequestMapping(value = "/schedule/content/date/timesolt", method = RequestMethod.GET)
    public List<Schedule> getScheduleByContendAndDateWithTimeSolt(@RequestParam(value = "content", required = true) String content, @RequestParam(value = "timeSolt", required = true) String timeSolt, @RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
        Date start = WeekUtil.parse(startDate);
        Date end  = WeekUtil.parse(endDate);
        return scheduleService.getScheduleByContentAndDateWithTimeSolt(content, timeSolt, start, end);
    }
    
    //@RequestMapping(value = "/schedule/timesolt", method = RequestMethod.GET)
    public List<Schedule> getScheduleByTimeSolt(@RequestParam(value = "timeSolt", required = true) String timeSolt) {
        return scheduleService.getScheduleByTimeSolt(timeSolt);
    }
    
    //@RequestMapping(value = "/schedule/timesolt/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByTimeSoltAndDate(@RequestParam(value = "timeSolt", required = true) String timeSolt,@RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {

        // return scheduleService.getScheduleByTimeSoltAndDate(timeSolt, start, end);
        return null;
    }
}

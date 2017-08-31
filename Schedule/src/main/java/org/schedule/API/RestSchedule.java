package org.schedule.API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.schedule.entity.Schedule;
import org.schedule.service.ScheduleService;
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
	
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public List<Schedule> getScheduleByName(@RequestParam(value = "name", required = true) String name) {
        return scheduleService.getScheduleByName(name);
    }
	
    @RequestMapping(value = "/schedule/name/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByNameAndDate(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
	    Date start = sdf.parse(startDate);
        Date end  = sdf.parse(endDate);
	    return scheduleService.getScheduleByNameAndDate(name, start, end);
    }
	
    @RequestMapping(value = "/schedule/name/date/timesolt", method = RequestMethod.GET)
    public List<Schedule> getScheduleByNameAndDateWithTimeSolt(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "timeSolt", required = true) String timeSolt, @RequestParam(value = "startDate", required = true) String startDate, @RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
        Date start = sdf.parse(startDate);
        Date end  = sdf.parse(endDate);
        return scheduleService.getScheduleByNameAndDateWithTimeSolt(name, timeSolt, start, end);
    }
	
    @RequestMapping(value = "/schedule/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByDate(@RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
	    Date start = sdf.parse(startDate);
	    Date end  = sdf.parse(endDate);
	    return scheduleService.getScheduleByDate(start, end);
    }
    
    @RequestMapping(value = "/schedule/content", method = RequestMethod.GET)
    public List<Schedule> getScheduleByContend(@RequestParam(value = "content", required = true) String content) {
        return scheduleService.getScheduleByContent(content);
    }
    
    @RequestMapping(value = "/schedule/content/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByContendAndDate(@RequestParam(value = "content", required = true) String content,@RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
        Date start = sdf.parse(startDate);
        Date end  = sdf.parse(endDate);
        return scheduleService.getScheduleByContentAndDate(content, start, end);
    }
    
    @RequestMapping(value = "/schedule/content/date/timesolt", method = RequestMethod.GET)
    public List<Schedule> getScheduleByContendAndDateWithTimeSolt(@RequestParam(value = "content", required = true) String content, @RequestParam(value = "timeSolt", required = true) String timeSolt, @RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        
        Date start = sdf.parse(startDate);
        Date end  = sdf.parse(endDate);
        return scheduleService.getScheduleByContentAndDateWithTimeSolt(content, timeSolt, start, end);
    }
    
    @RequestMapping(value = "/schedule/timesolt", method = RequestMethod.GET)
    public List<Schedule> getScheduleByTimeSolt(@RequestParam(value = "timeSolt", required = true) String timeSolt) {
        return scheduleService.getScheduleByTimeSolt(timeSolt);
    }
    
    @RequestMapping(value = "/schedule/timesolt/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByTimeSoltAndDate(@RequestParam(value = "timeSolt", required = true) String timeSolt,@RequestParam(value = "startDate", required = true) String startDate,@RequestParam(value = "endDate", required = true) String endDate) throws ParseException {

        Date start = sdf.parse(startDate);
        Date end  = sdf.parse(endDate);
        return scheduleService.getScheduleByTimeSoltAndDate(timeSolt, start, end);
    }
}

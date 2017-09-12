package org.schedule.API;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.internal.ExactComparisonCriteria;
import org.schedule.entity.Level;
import org.schedule.entity.Schedule;
import org.schedule.entity.Week;
import org.schedule.service.ScheduleService;
import org.schedule.util.ExportDataToExcelUtil;
import org.schedule.util.WeekUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jxl.write.Boolean;

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
    public List<Level> getLeaderName(@RequestParam(value = "name", required = false) String name) {
    	if(name == null || name.equals("")){
    		return scheduleService.getAllLeaderName();
        }
    	
        return scheduleService.getLeaderName(name);
    }
    
    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public Week getCurrentWeekSEDate() throws ParseException {
       // String currentDate =  WeekUtil.format(new Date());
       
      //  Date current = WeekUtil.parse(currentDate);
        //int dayNumber = WeekUtil.getWeekNumber(current);
       // String[] array = {WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber)};
        return null; //new Week(WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber));
    }
    
    @RequestMapping(value = "/dateall", method = RequestMethod.GET)
    public Week getAllWeekDate(@RequestParam(value = "currentDate", required = false) String currentDate) throws ParseException {
    	Date current = null;
    	if (currentDate ==null || currentDate.equals("")) {
    	    current =  new Date();
       	}else{
            current =  WeekUtil.parse(currentDate);
       	}
        int dayNumber = WeekUtil.getWeekNumber(current);
        String[] array = {WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber)};
        return new Week(array , WeekUtil.getAllWeekString(current, dayNumber));
    }
    
    @RequestMapping(value = "/dateall/last", method = RequestMethod.GET)
    public Week getLastWeekDate(@RequestParam(value = "currentDate", required = false) String currentDate) throws ParseException {
        Date current = null;
        if (currentDate == null || currentDate.equals("")) {
            current =  new Date();
        } else {
            current =  WeekUtil.parse(currentDate);
        }
		
        int dayNumber = WeekUtil.getWeekNumber(current);
        Date lastCurrent  = WeekUtil.parse(WeekUtil.getFirstDayOfLastWeek(current, dayNumber));
        dayNumber = WeekUtil.getWeekNumber(lastCurrent);
        String[] array = {WeekUtil.getFirstDayOfCurrentWeek(lastCurrent, dayNumber), WeekUtil.getLastDayOfCurrentWeek(lastCurrent, dayNumber)};
        return  new Week(array, WeekUtil.getAllWeekString(lastCurrent, dayNumber));
    }
    
    @RequestMapping(value = "/dateall/next", method = RequestMethod.GET)
    public Week getNextWeekDate(@RequestParam(value = "currentDate", required = false) String currentDate) throws ParseException {
       Date current = null;
        if (currentDate == null || currentDate.equals("")) {
            current =  new Date();
        } else {
            current =  WeekUtil.parse(currentDate);
        }
		
        int dayNumber = WeekUtil.getWeekNumber(current);
        Date nextCurrent  = WeekUtil.parse(WeekUtil.getFirstDayOfNextWeek(current, dayNumber));
        dayNumber = WeekUtil.getWeekNumber(nextCurrent);
        String[] array = {WeekUtil.getFirstDayOfCurrentWeek(nextCurrent, dayNumber), WeekUtil.getLastDayOfCurrentWeek(nextCurrent, dayNumber)};
        return  new Week(array, WeekUtil.getAllWeekString(nextCurrent, dayNumber)); //new Week(WeekUtil.format(nextCurrent), WeekUtil.getLastDayOfNextWeek(current, dayNumber), WeekUtil.getAllWeek(nextCurrent, WeekUtil.getWeekNumber(nextCurrent)));
    }
	
    @RequestMapping(value = "/schedule/name/date", method = RequestMethod.GET)
    public List<Schedule> getScheduleByNameAndDate(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "currentDate", required = false) String currentDate) throws ParseException {		
		Date current = null;
        if (currentDate ==null || currentDate.equals("")) {
            current =  new Date();
            currentDate = WeekUtil.format(current);
        }
    	
    	if( name == null || name.equals("")) {
            return scheduleService.getScheduleByDate(currentDate);
        }
        
        return scheduleService.getScheduleByNameAndDate(name, currentDate);
    }
	
    @RequestMapping(value = "/schedule/name/date/next", method = RequestMethod.GET)
    public List<Schedule> getNextScheduleByNameAndDate(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "currentDate", required = true) String currentDate) throws ParseException {
        Date current = WeekUtil.parse(currentDate);
        int dayNumber = WeekUtil.getWeekNumber(current);
        Date nextCurrent  = WeekUtil.parse(WeekUtil.getFirstDayOfNextWeek(current, dayNumber));
        if( name == null || name.equals("")) {
            return scheduleService.getScheduleByDate(WeekUtil.format(nextCurrent));
        }
        
        return scheduleService.getScheduleByNameAndDate(name, WeekUtil.format(nextCurrent));
    }
    
    @RequestMapping(value = "/schedule/name/date/last", method = RequestMethod.GET)
    public List<Schedule> getLastScheduleByNameAndDate(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "currentDate", required = true) String currentDate) throws ParseException {
        Date current = WeekUtil.parse(currentDate);
        int dayNumber = WeekUtil.getWeekNumber(current);
        Date lastCurrent  = WeekUtil.parse(WeekUtil.getFirstDayOfLastWeek(current, dayNumber));
        if( name == null || name.equals("")) {
            return scheduleService.getScheduleByDate(WeekUtil.format(lastCurrent));
        }
        
        return scheduleService.getScheduleByNameAndDate(name, WeekUtil.format(lastCurrent));
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
    
    @SuppressWarnings({ "finally", "null" })
	@RequestMapping(value = "/schedule/downloadAgenda", method = RequestMethod.GET)
    @ResponseBody
    public boolean downloadAgenda(@RequestParam(value = "leader", required = false) String name, @RequestParam(value = "currentDate", required = true) String currentDate,HttpServletRequest req, HttpServletResponse response) throws ParseException {
    	boolean isSuccess=false;
    	try {
    		String generateFilePath="error";
        	Date current = WeekUtil.parse(currentDate);
        	if (currentDate ==null || currentDate.equals("")) {
        	    current =  new Date();
           	}else{
                current =  WeekUtil.parse(currentDate);
           	}
            int dayNumber = WeekUtil.getWeekNumber(current);
            ExportDataToExcelUtil exportDataToExcel=new ExportDataToExcelUtil(name,current,dayNumber);
            String[] array = {WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber), WeekUtil.getLastDayOfCurrentWeek(current, dayNumber)};
            Date currentWeekFirstDay  = WeekUtil.parse(WeekUtil.getFirstDayOfCurrentWeek(current, dayNumber));
            if( name == null || name.equals("")) {
            	generateFilePath=exportDataToExcel.generateExcelSheetHeader(scheduleService.getAllLeaderName(), new Week(array , WeekUtil.getAllWeekString(current, dayNumber))
            			,scheduleService.getScheduleByDate(WeekUtil.format(currentWeekFirstDay)));
            }else {
            	generateFilePath=exportDataToExcel.generateExcelSheetHeader(scheduleService.getLeaderName(name), new Week(array , WeekUtil.getAllWeekString(current, dayNumber))
    					,scheduleService.getScheduleByNameAndDate(name, WeekUtil.format(currentWeekFirstDay)));
    		}
            if(generateFilePath!=null||generateFilePath.equals("")) {
            	exportDataToExcel.downloadAgenda(req, response,generateFilePath);
            }
            isSuccess=true;
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess=false;
		}finally {
			return isSuccess;	
		}
    }
}

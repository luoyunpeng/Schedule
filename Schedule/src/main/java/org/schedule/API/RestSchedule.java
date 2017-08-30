package org.schedule.API;

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

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public List<Schedule> getScheduleByName(@RequestParam(value = "name", required = true) String name) {
		return scheduleService.getScheduleByName(name);
	}
	
	@RequestMapping(value = "/scheduletime", method = RequestMethod.GET)
	public List<Schedule> getScheduleByNameAndTime(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "start", required = true) Date startDate,@RequestParam(value = "end", required = true) Date endDate) {
		return scheduleService.getScheduleByName(name);
	}
	
}

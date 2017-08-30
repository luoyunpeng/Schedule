package org.schedule;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schedule.entity.Schedule;
import org.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        //create table Schedule(id int not null auto_increment , name varchar(20), jobNumber varchar(50), content varchar(1000), address varchar(100), time date, timeSolt varchar(10), relatedPeopleAndDep varchar(200), comment varchar(100), primary key(id));
        jdbcTemplate.update("delete from Schedule");
    }

    @Test
    public void testBatchLoad() {
        List<Schedule> list = new ArrayList<Schedule>();

        Schedule schedule = null;
        for (int i = 1; i <= 100; i++) {
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Date());
            schedule.setTimeSolt("上午");
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setComment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);

        assertEquals(100 + "", jdbcTemplate.queryForObject("select count(*) from Schedule", Integer.class) + "");
    }

    @Test
    public void testGetByName() {
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        for (int i = 1; i <= 20; i++) {
        	 schedule = new Schedule();
             schedule.setName("test-" + i);
             schedule.setContent("do something--" + i);
             schedule.setTime(new Date());
             schedule.setTimeSolt("pm");
             schedule.setRelatedPeopleAndDep("something related");
             schedule.setAddress("beijing");
             schedule.setComment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);
        System.out.println(scheduleService.getScheduleByName("test-15").get(0));
        assertEquals("test-15", scheduleService.getScheduleByName("test-15").get(0).getName());
    }

    @Test
    public void testGetByContent() {
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        for (int i = 1; i <= 20; i++) {
        	 schedule = new Schedule();
             schedule.setName("test-" + i);
             schedule.setContent("do something--" + i);
             schedule.setTime(new Date());
             schedule.setTimeSolt("上午");
             schedule.setRelatedPeopleAndDep("something related");
             schedule.setAddress("beijing");
             schedule.setComment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);

        assertEquals("上午", scheduleService.getScheduleByContent("%some%").get(0).getTimeSolt());
    }

    @Test
    public void testTime() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        Date date = null;
        for (int i = 1; i <= 20; i++) {
        	if(i>=10) {
        		 date = sdf.parse("2008-07-" + i);
        	}else {
        		 date = sdf.parse("2008-07-0" + i);
        	}
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(date);
            schedule.setTimeSolt("pm");
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setComment("IT" + i);

            list.add(schedule);
        }
        scheduleService.loadSchedule(list);
        
        Date start = sdf.parse("2008-07-08");
        
        Date end = sdf.parse("2008-07-16");
        assertEquals(9, scheduleService.getScheduleByTime(start, end).size());
    }
    
    @Test
    public void testNameAndTime() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        Date date = null;
        for (int i = 1; i <= 20; i++) {
        	if(i>=10) {
        		 date = sdf.parse("2008-07-" + i);
        	}else {
        		 date = sdf.parse("2008-07-0" + i);
        	}
        	
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(date);
            schedule.setTimeSolt("pm");
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setComment("IT" + i);

            list.add(schedule);
        }
        scheduleService.loadSchedule(list);
        
        Date start = sdf.parse("2008-07-08");
        
        Date end = sdf.parse("2008-07-16");
        assertEquals(9, scheduleService.getScheduleByNameAndTime("test-1", start, end).size());
    }

}

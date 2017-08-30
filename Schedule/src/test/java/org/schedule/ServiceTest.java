package org.schedule;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schedule.entity.Schedule;
import org.schedule.service.SchduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    SchduleService scheduleService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        //create table Schedule(id int not null auto_increment , name varchar(20), jobNumber varchar(50), content varchar(1000), address varchar(100), time timestamp, relatedPeopleAndDep varchar(200), department varchar(50), primary key(id))
        jdbcTemplate.update("delete from Schedule");
    }

    @Test
    public void testBatchLoad() {
        List<Schedule> list = new ArrayList<Schedule>();

        Schedule schedule = null;
        for (int i = 1; i <= 100; i++) {
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setJobNumber("00810086--" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Timestamp(new Date().getTime()));
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setDepartment("IT" + i);

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
            schedule.setJobNumber("10086" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Timestamp(new Date().getTime()));
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setDepartment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);
        System.out.println(scheduleService.getScheduleByName("test-15").get(0));
        assertEquals("test-15", scheduleService.getScheduleByName("test-15").get(0).getName());
    }

    @Test
    public void testGetByJobNumber() {
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        for (int i = 1; i <= 20; i++) {
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setJobNumber("10086" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Timestamp(new Date().getTime()));
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setDepartment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);

        assertEquals("1008615", scheduleService.getScheduleByJobNumber("1008615").get(0).getJobNumber());
    }

    @Test
    public void testGetByDep() {
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        for (int i = 1; i <= 20; i++) {
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setJobNumber("10086" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Timestamp(new Date().getTime()));
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setDepartment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);

        assertEquals("IT15", scheduleService.getScheduleByDepartment("IT15").get(0).getDepartment());
    }

    @Test
    public void testGetByContent() {
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        for (int i = 1; i <= 20; i++) {
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setJobNumber("10086" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Timestamp(new Date().getTime()));
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setDepartment("IT" + i);

            list.add(schedule);
        }

        scheduleService.loadSchedule(list);

        assertEquals("do something--15", scheduleService.getScheduleByContent("do something--15").get(0).getContent());
    }

    @Test
    public void testTime() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Schedule> list = new ArrayList<Schedule>();
        Schedule schedule = null;
        for (int i = 1; i <= 20; i++) {
            Date date = sdf.parse("2008-07-" + i + " 19:20:00");
            schedule = new Schedule();
            schedule.setName("test-" + i);
            schedule.setJobNumber("10086" + i);
            schedule.setContent("do something--" + i);
            schedule.setTime(new Timestamp(date.getTime()));
            schedule.setRelatedPeopleAndDep("something related");
            schedule.setAddress("beijing");
            schedule.setDepartment("IT" + i);

            list.add(schedule);
        }
        scheduleService.loadSchedule(list);
        
        Date date = sdf.parse("2008-07-08 13:30:00");
        Timestamp start = new Timestamp(date.getTime());
        
        date = sdf.parse("2008-07-16 13:30:00");
        Timestamp end = new Timestamp(date.getTime());
        assertEquals(8, scheduleService.getScheduleByTime(start, end).size());
    }

}

package org.schedule.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.schedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final static RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<Schedule>(Schedule.class);

    public int loadSchedule(List<Schedule> list) {
        //Schedule(name,jobNumber,content,address,time,relatedPeopleAndDep,department)
        String sql = "insert into Schedule(name,jobNumber,content,address,time,relatedPeopleAndDep,department) values (?, ?, ?, ?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (Schedule schedule : list) {
            batchArgs.add(new Object[] { schedule.getName(), schedule.getJobNumber(),schedule.getContent(), schedule.getAddress(), schedule.getTime(), schedule.getRelatedPeopleAndDep(), schedule.getDepartment() });
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);

        return 0;
    }

    public List<Schedule> getScheduleByName(String name) {
        // List<Schedule> schedules = jdbcTemplate.query("select * from Schedule where
        // name = " + name, rowMapper);

        return jdbcTemplate.query("select * from Schedule where name = ?", rowMapper, name);
    }
    
    public List<Schedule> getScheduleByTime(Timestamp startTime, Timestamp endTime) {
        // List<Schedule> schedules = jdbcTemplate.query("select * from Schedule where
        // name = " + name, rowMapper);

        return jdbcTemplate.query("select * from Schedule where time >= ? and  time <= ?", rowMapper, new Object[] { startTime, endTime});
    }

    public List<Schedule> getScheduleByJobNumber(String jobNumber) {

        return jdbcTemplate.query("select * from Schedule where jobNumber = ?", rowMapper, jobNumber);
    }

    public List<Schedule> getScheduleByDepartment(String department) {

        return jdbcTemplate.query("select * from Schedule where department = ?", rowMapper, department);
    }

    public List<Schedule> getScheduleByContent(String content) {

        return jdbcTemplate.query("select * from Schedule where content = ?", rowMapper, content);
    }
    
    public List<Schedule> getScheduleByTimestamp(Timestamp time) {

        return jdbcTemplate.query("select * from Schedule where content = ?", rowMapper, "");
    }

}

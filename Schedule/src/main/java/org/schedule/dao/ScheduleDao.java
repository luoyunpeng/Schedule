package org.schedule.dao;

import java.util.ArrayList;
import java.util.Date;
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

    // batch load with list
    public int loadSchedule(List<Schedule> list) {
        //Schedule(name,content,address,time,timeSolt,relatedPeopleAndDep,comment)
        String sql = "insert into Schedule(name,content,address,time,timeSolt,relatedPeopleAndDep,comment) values (?, ?, ?, ?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (Schedule schedule : list) {
            batchArgs.add(new Object[] { schedule.getName(), schedule.getContent(), schedule.getAddress(), schedule.getTime(),schedule.getTimeSolt(), schedule.getRelatedPeopleAndDep(), schedule.getComment() });
        }
        
        int[] rs = jdbcTemplate.batchUpdate(sql, batchArgs);
        int totalInsert = 0;
        for (int i = 0; i < rs.length; i++) {
            totalInsert += rs[i];
        }
        
        return totalInsert;
    }
    

    //select with name
    public List<Schedule> getScheduleByName(String name) {

        return jdbcTemplate.query("select * from Schedule where name = ?", rowMapper, name);
    }
    
    //select with name and date
    public List<Schedule> getScheduleByNameAndDate(String name, Date startDate, Date endDate) {

        return jdbcTemplate.query("select * from Schedule where name = ? and time >= ? and  time <= ?", rowMapper,  new Object[] { name, startDate, endDate});
    }
    
    //select with name, date and timesolt
    public List<Schedule> getScheduleByNameAndDateWithTimeSolt(String name, String timeSolt, Date startDate, Date endDate) {

        return jdbcTemplate.query("select * from Schedule where name = ? and time >= ? and  time <= ? and timeSolt = ?", rowMapper,  new Object[] { name, startDate, endDate, timeSolt});
    }
    
    //select with date
    public List<Schedule> getScheduleByDate(Date startDate, Date endDate) {

        return jdbcTemplate.query("select * from Schedule where time >= ? and  time <= ?", rowMapper,  new Object[] {startDate, endDate});
    }

    //select with content
    public List<Schedule> getScheduleByContent(String content) {

        return jdbcTemplate.query("select * from Schedule where content  like ?", rowMapper, content);
    }
    
    //select with content and date
    public List<Schedule> getScheduleByContentAndDate(String content, Date startDate, Date endDate) {

        return jdbcTemplate.query("select * from Schedule where content  like ? and time >= ? and  time <= ?", rowMapper, new Object[] { content, startDate, endDate});
    }
    
    //select with content, date and timesolt
    public List<Schedule> getScheduleByContentAndDateWithTimeSolt(String content, String timeSolt, Date startDate, Date endDate) {

        return jdbcTemplate.query("select * from Schedule where content  like ? and time >= ? and  time <= ? and timeSolt = ?", rowMapper, new Object[] { content, startDate, endDate, timeSolt});
    }
    
    //select with timesolt
    public List<Schedule> getScheduleByTimeSolt(String timeSolt) {

        return jdbcTemplate.query("select * from Schedule where timeSolt = ?", rowMapper, timeSolt);
    }
    
    //select with timesolt and date
    public List<Schedule> getScheduleByTimeSoltAndDate(String timeSolt, Date startDate, Date endDate) {

        return jdbcTemplate.query("select * from Schedule where timeSolt  = ? and time >= ? and  time <= ?", rowMapper, new Object[] { timeSolt, startDate, endDate});
    }
}

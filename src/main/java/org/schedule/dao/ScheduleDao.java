package org.schedule.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.schedule.entity.Level;
import org.schedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.sym.Name;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;

@Repository
public class ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final static RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<Schedule>(Schedule.class);
    private final static RowMapper<Level> rowMapperLevel = new BeanPropertyRowMapper<Level>(Level.class);

    // batch load with list
    public int loadSchedule(List<Schedule> list) {
        //Schedule(name,content,address,time,periodId,relatedPeopleAndDep,comment, level)
        String sql = "insert into Schedule(name,content,address,time,periodId,relatedPeopleAndDep,comment) values (?, ?, ?, ?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (Schedule schedule : list) {
            batchArgs.add(new Object[] { schedule.getName(), schedule.getContent(), schedule.getAddress(), schedule.getTime(),schedule.getPeriodId(), schedule.getRelatedPeopleAndDep(), schedule.getComment() });
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
    
    //select all leader
    public List<Level> getAllLeader() {

        return jdbcTemplate.query("select * from level order by level", rowMapperLevel);
    }
    
    public List<Level> getLeader(String name) {

        return jdbcTemplate.query("select * from level where leaderName = ?", rowMapperLevel,name);
    }
    
    //select with name and date
    public List<Schedule> getScheduleByNameAndDate(String name, String startDate, String endDate) {
    								//select * from Schedule s right join level l on s.name=l.leaderName and time >= ? and  time <= ? order by time, periodId
        //System.out.println("select * from Schedule s right join level l on s.name=l.leaderName and s.name="+name+" and time >= "+startDate+" and  time <= "+endDate+" order by time, periodId", rowMapper,  new Object[] { name, startDate, endDate});
    	//return jdbcTemplate.query("select * from Schedule s right join level l on s.name=l.leaderName and time >= ? and  time <= ? where s.name =? order by time, periodId", rowMapper,  new Object[] { startDate, endDate, name});
    	return jdbcTemplate.query("select * from Schedule s right join level l on s.name=l.leaderName  where time >= ? and  time <= ? and s.name =? order by time, periodId", rowMapper,  new Object[] { startDate, endDate, name});
    }
    
    
    //select with name, date and timesolt
    public List<Schedule> getScheduleByNameAndDateWithTimeSolt(String name, String timeSolt, String startDate, String endDate) {

        return jdbcTemplate.query("select * from Schedule where name = ? and time >= ? and  time <= ? and timeSolt = ?", rowMapper,  new Object[] { name, startDate, endDate, timeSolt});
    }
    
    //select with date
    public List<Schedule> getScheduleByDate(String startDate, String endDate) {

        return jdbcTemplate.query("select * from Schedule s right join level l on s.name=l.leaderName where time >= ? and  time <= ? order by time, periodId, level", rowMapper,  new Object[] {startDate, endDate});
    }

    //select with content
    public List<Schedule> getScheduleByContent(String content, String startDate, String endDate) {

        return jdbcTemplate.query("select * from Schedule s right join level l where s.name=l.leaderName and time >= ? and  time <= ? order by time, periodId, level", rowMapper, new Object[] { content, startDate, endDate});
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
    
    //delete with name and date
    public int deleteScheduleByNameAndDate(String name,String startDate,String endDate){
    	//select * from Schedule s right join level l on s.name=l.leaderName  where time >= ? and  time <= ? and s.name =? order by time, periodId
    	String sql="DELETE s FROM Schedule s RIGHT JOIN level l ON s.name=l.leaderName WHERE s.time >= ? AND s.time <= ? AND s.name =?";
    	return jdbcTemplate.update(sql, startDate,endDate,name);
    }
}
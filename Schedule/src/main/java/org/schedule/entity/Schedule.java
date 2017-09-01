package org.schedule.entity;

import java.util.Date;

import org.schedule.util.WeekUtil;

public class Schedule {
    private String name;
    private String content;
    private String address;
    private Date time;
    private String week;
    private int dayId;
    private String timeSolt;
    private int periodId;
    private int level;
    
    private String relatedPeopleAndDep;

    private String comment;

    public Schedule(){}

    public Schedule(String name, String content, String address, Date time, String timeSolt, String relatedPeopleAndDep,
                     String comment) {
             super();
             this.name = name;
             this.content = content;
             this.address = address;
             this.time = time;
             this.timeSolt = timeSolt;
             if(timeSolt.equals("上午")) {
                 this.setPeriodId(1);
             }else if(timeSolt.equals("下午")) {
                 this.setPeriodId(3);
             } 
             
             this.relatedPeopleAndDep = relatedPeopleAndDep;
             this.comment = comment;
    }

    public String getAddress() {
        return address;
    }

    public String getComment() {
        return comment;
    }

    public String getContent() {
        return content;
    }
    public int getDayId() {
        return dayId;
    }
    
    public int getLevel() {
        return level;
    }
    
    public String getName() {
        return name;
    }

    public int getPeriodId() {
        return periodId;
    }

    public String getRelatedPeopleAndDep() {
        return relatedPeopleAndDep;
    }

    public Date getTime() {
        return time;
    }

    public String getTimeSolt() {
        return timeSolt;
    }

    public String getWeek() {
        return week;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeriodId(int periodId) {
        if(periodId == 1) {
            this.setTimeSolt("上午");
        }else if (periodId == 3) {
            this.setTimeSolt("下午");
        }
        
        this.periodId = periodId;
    }

    public void setRelatedPeopleAndDep(String relatedPeopleAndDep) {
        this.relatedPeopleAndDep = relatedPeopleAndDep;
    }

    public void setTime(Date time) {
		if(time != null){
			this.setWeek( WeekUtil.getWeek(time));
			this.setDayId(WeekUtil.getWeekNumber(time));
		}
        this.time = time;
    }

    public void setTimeSolt(String timeSolt) {  
        
        this.timeSolt = timeSolt;
    }
    
    public void setWeek(String week) {
        this.week = week;
    }

    public String toString() {
        return name;
    }
}

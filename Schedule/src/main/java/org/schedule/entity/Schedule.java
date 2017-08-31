package org.schedule.entity;

import java.util.Date;

import org.schedule.util.WeekUtil;

public class Schedule {
    private String name;
    private String content;
    private String address;
    private Date time;
    private String week;
    private String timeSolt;
    private String relatedPeopleAndDep;
    private String comment;
    
    public String getAddress() {
        return address;
    }
    
    public String getComment() {
        return comment;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setRelatedPeopleAndDep(String relatedPeopleAndDep) {
        this.relatedPeopleAndDep = relatedPeopleAndDep;
    }

    public void setTime(Date time) {
	    this.setWeek( WeekUtil.getWeek(time));
	    this.time = time;
    }

    public void setTimeSolt(String timeSolt) {
        this.timeSolt = timeSolt;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String toString() {
        return name  + content + time + relatedPeopleAndDep + comment + address;
    }
}

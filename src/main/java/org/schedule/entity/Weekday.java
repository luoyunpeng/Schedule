package org.schedule.entity;

import java.util.Date;

public class Weekday {
    private int dayId;
    private Date currentDate;
    private String week;
    
    public Weekday(int dayId, Date currentDate, String week) {
        this.dayId = dayId;
        this.currentDate = currentDate;
        this.week = week;
    }
    
    public int getDayId() {
        return dayId;
    }
    
    public void setDayId(int dayId) {
        this.dayId = dayId;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }
    
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    public String getWeek() {
        return week;
    }
    
    public void setWeek(String week) {
        this.week = week;
    }
    
    public String toString() {
        return dayId+"    "+ currentDate+"    " +week;
    }
}

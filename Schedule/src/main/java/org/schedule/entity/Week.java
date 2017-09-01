package org.schedule.entity;

public class Week {
    private String startDate;
    private String endDate;
    
    public Week(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

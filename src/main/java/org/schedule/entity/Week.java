package org.schedule.entity;

public class Week {
    private String[] startDate;
    private String[] listWeek;
    private String[] period= {"上午", "下午"};
    
    public Week(String[] startDate, String[] listWeek) {
        this.startDate = startDate;
        this.listWeek = listWeek;
    }
    
    public String[] getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String[] startDate) {
        this.startDate = startDate;
    }
    
    public String[] getListWeek() {
        return listWeek;
    }
    
    public void setListWeek(String[] listWeek) {
        this.listWeek = listWeek;
    }
    
    public String[] getPeriod() {
        return period;
    }
    
    public void setPeriod(String[] period) {
        this.period = period;
    }
}

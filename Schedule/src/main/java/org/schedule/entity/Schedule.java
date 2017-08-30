package org.schedule.entity;

import java.sql.Timestamp;

public class Schedule {
    private String name;
    private String jobNumber;
    private String content;
    private String address;
    private Timestamp time;
    private String relatedPeopleAndDep;
    private String department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelatedPeopleAndDep() {
        return relatedPeopleAndDep;
    }

    public void setRelatedPeopleAndDep(String relatedPeopleAndDep) {
        this.relatedPeopleAndDep = relatedPeopleAndDep;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String toString() {
        return name + jobNumber + content + time + relatedPeopleAndDep + department + address;
    }

}

package org.schedule.entity;

import java.util.Date;

public class Schedule {
    private String name;
    private String content;
    private String address;
    private Date time;
    private String timeSolt;
    private String relatedPeopleAndDep;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTimeSolt() {
		return timeSolt;
	}

	public void setTimeSolt(String timeSolt) {
		this.timeSolt = timeSolt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

    public String toString() {
        return name  + content + time + relatedPeopleAndDep + comment + address;
    }

}

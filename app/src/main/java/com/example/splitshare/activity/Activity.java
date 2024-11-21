package com.example.splitshare.activity;

import java.util.Date;

public class Activity {

    private Integer activityID;
    private String activityDescription;
    private Date activityDate;

    public Activity() {
    }

    public Activity(String activityDescription, Date activityDate) {
        this.activityDescription = activityDescription;
        this.activityDate = activityDate;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityID=" + activityID +
                ", activityDescription='" + activityDescription + '\'' +
                ", activityDate=" + activityDate +
                '}';
    }
}

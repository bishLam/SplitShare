package com.example.splitshare.activity;

import androidx.room.ColumnInfo;

import java.util.Date;

public class Activity {

    @ColumnInfo(name = "ACTIVITY_ID")
    private Integer activityID;
    @ColumnInfo(name = "RECEIPT_ID")
    private Integer receiptID;
    @ColumnInfo(name = "RECEIPT_DESCRIPTION")
    private String receiptDescription;
    @ColumnInfo(name = "RECEIPT_AMOUNT")
    private Double receiptAmount;
    @ColumnInfo(name = "RECEIPT_DATE")
    private Date receiptDate;
    @ColumnInfo(name = "SPENDER_FIRST_NAME")
    private String spenderFirstName;
    @ColumnInfo(name = "SPENDER_LAST_NAME")
    private String spenderLastName;
    @ColumnInfo(name = "SPLITTED_AMOUNT")
    private Double splittedAmount;
    @ColumnInfo(name = "GROUP_ID")
    private Integer groupID;
    @ColumnInfo(name = "GROUP_NAME")
    private String groupName;
    @ColumnInfo(name = "STATUS")
    private String status;


    public Activity() {
    }

    public Activity(Integer activityID, Integer receiptID, String groupName, String receiptDescription, Double receiptAmount, Date receiptDate, String spenderFirstName, String spenderLastName, Double splittedAmount, Integer groupID, String status) {
        this.receiptID = receiptID;
        this.activityID = activityID;
        this.receiptDescription = receiptDescription;
        this.receiptAmount = receiptAmount;
        this.receiptDate = receiptDate;
        this.spenderFirstName = spenderFirstName;
        this.spenderLastName = spenderLastName;
        this.splittedAmount = splittedAmount;
        this.groupID = groupID;
        this.status = status;
        this.groupName = groupName;
    }

    public Integer getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Integer receiptID) {
        this.receiptID = receiptID;
    }

    public String getReceiptDescription() {
        return receiptDescription;
    }

    public void setReceiptDescription(String receiptDescription) {
        this.receiptDescription = receiptDescription;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getSpenderFirstName() {
        return spenderFirstName;
    }

    public void setSpenderFirstName(String spenderFirstName) {
        this.spenderFirstName = spenderFirstName;
    }

    public String getSpenderLastName() {
        return spenderLastName;
    }

    public void setSpenderLastName(String spenderLastName) {
        this.spenderLastName = spenderLastName;
    }

    public Double getSplittedAmount() {
        return splittedAmount;
    }

    public void setSplittedAmount(Double splittedAmount) {
        this.splittedAmount = splittedAmount;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }
}

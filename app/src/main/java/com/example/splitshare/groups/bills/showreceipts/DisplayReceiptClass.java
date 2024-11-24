package com.example.splitshare.groups.bills.showreceipts;


import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import java.util.Date;

public class DisplayReceiptClass {
    @ColumnInfo(name = "RECEIPT_ID")
    private Integer receiptID;

    @ColumnInfo(name = "RECEIPT_DESCRIPTION")
    private String receiptDescription;

    @ColumnInfo(name = "RECEIPT_AMOUNT")
    private Double receiptAmount;

    @ColumnInfo(name = "RECEIPT_DATE")
    private Date receiptDate;

    @ColumnInfo(name = "USER_ID")
    private Integer userID;

    @ColumnInfo(name = "GROUP_ID")
    private Integer groupID;

    @ColumnInfo(name = "FIRST_NAME")
    private String firstName;

    @ColumnInfo(name = "LAST_NAME")
    private String lastName;

    @Ignore
    public DisplayReceiptClass() {
    }

    public DisplayReceiptClass(String receiptDescription, Double receiptAmount, Date receiptDate, Integer userID, Integer groupID, String firstName, String lastName) {
        this.receiptDescription = receiptDescription;
        this.receiptAmount = receiptAmount;
        this.receiptDate = receiptDate;
        this.userID = userID;
        this.groupID = groupID;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}


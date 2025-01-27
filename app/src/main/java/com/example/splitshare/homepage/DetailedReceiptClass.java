package com.example.splitshare.homepage;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import java.util.Date;

public class DetailedReceiptClass {
    @ColumnInfo(name = "RECEIPT_ID")
    private String receiptID;
    @ColumnInfo(name = "FIRST_NAME")
    private String firstName;
    @ColumnInfo(name = "LAST_NAME")
    private String lastName;
    @ColumnInfo(name = "DATE")
    private Date receiptDate;
    @ColumnInfo(name = "AMOUNT")
    private Double amount;
    @ColumnInfo(name = "GROUP_NAME")
    private String groupName;

    @Ignore
    public DetailedReceiptClass() {
    }

    public DetailedReceiptClass(String receiptID, String firstName, String lastName, Date receiptDate, Double amount, String groupName) {
        this.receiptID = receiptID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.groupName = groupName;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
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

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "ConfirmReceiptDetailedReceiptClass{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", receiptDate=" + receiptDate +
                ", amount=" + amount +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}

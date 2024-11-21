package com.example.splitshare.groups.bills;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "RECEIPT" ,
        indices = {
                @Index(value = "RECEIPT_ID"),
                @Index(value = "USER_ID"),
                @Index(value = "GROUP_ID")
        }
)
public class Receipt {

    @PrimaryKey(autoGenerate = true)
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

    // Constructors, getters, and setters

    @Ignore
    public Receipt() {
    }

    public Receipt(String receiptDescription, Double receiptAmount, Date receiptDate, Integer userID, Integer groupID) {
        this.receiptDescription = receiptDescription;
        this.receiptAmount = receiptAmount;
        this.receiptDate = receiptDate;
        this.userID = userID;
        this.groupID = groupID;
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

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptID=" + receiptID +
                ", receiptDescription='" + receiptDescription + '\'' +
                ", receiptAmount=" + receiptAmount +
                ", receiptDate='" + receiptDate + '\'' +
                ", userID=" + userID +
                ", groupID=" + groupID +
                '}';
    }
}

package com.example.splitshare.groups.bills.addreceipt.confirmreceipt;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ConfirmReceiptDetailedReceiptClass implements Serializable {
    private Date receiptDate;
    private Double receiptAmount;
    private String addedByUserName;
    private String receiptDescription;
    HashMap<User, Double> amountSplit;
    private Group group;

    public ConfirmReceiptDetailedReceiptClass() {
    }

    public ConfirmReceiptDetailedReceiptClass(Date receiptDate, Double receiptAmount, String addedByUserName, String receiptDescription, HashMap<User, Double> amountSplit, Group group) {
        this.receiptDate = receiptDate;
        this.receiptAmount = receiptAmount;
        this.addedByUserName = addedByUserName;
        this.receiptDescription = receiptDescription;
        this.amountSplit = amountSplit;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public HashMap<User, Double> getAmountSplit() {
        return amountSplit;
    }

    public void setAmountSplit(HashMap<User, Double> amountSplit) {
        this.amountSplit = amountSplit;
    }

    public String getReceiptDescription() {
        return receiptDescription;
    }

    public void setReceiptDescription(String receiptDescription) {
        this.receiptDescription = receiptDescription;
    }

    public String getAddedByUserName() {
        return addedByUserName;
    }

    public void setAddedByUserName(String addedByUserName) {
        this.addedByUserName = addedByUserName;
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
}

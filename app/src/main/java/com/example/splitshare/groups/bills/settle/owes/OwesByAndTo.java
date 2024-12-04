package com.example.splitshare.groups.bills.settle.owes;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class OwesByAndTo {
    @ColumnInfo(name = "SPLIT_ID")
    private Integer splitID;
    @ColumnInfo(name = "OWED_BY_UID")
    private Integer owedBy;
    @ColumnInfo(name = "OWED_BY_NAME")
    private String owedByName;
    @ColumnInfo(name = "OWED_BY_LAST_NAME")
    private String owedByLastName;
    @ColumnInfo(name = "OWED_TO_UID")
    private Integer owedTo;
    @ColumnInfo(name = "OWED_TO_NAME")
    private String owedToName;
    @ColumnInfo(name = "OWED_TO_LAST_NAME")
    private String owedToLastName;
    @ColumnInfo(name = "AMOUNT")
    private Double amount;

    @Ignore
    public OwesByAndTo() {
    }

    public OwesByAndTo(Integer owedBy, String owedByName, String owedByLastName, Integer owedTo, String owedToName, String owedToLastName, Double amount) {
        this.owedBy = owedBy;
        this.owedByName = owedByName;
        this.owedByLastName = owedByLastName;
        this.owedTo = owedTo;
        this.owedToName = owedToName;
        this.owedToLastName = owedToLastName;
        this.amount = amount;
    }

    public Integer getOwedBy() {
        return owedBy;
    }

    public void setOwedBy(Integer owedBy) {
        this.owedBy = owedBy;
    }

    public String getOwedByName() {
        return owedByName;
    }

    public void setOwedByName(String owedByName) {
        this.owedByName = owedByName;
    }

    public String getOwedByLastName() {
        return owedByLastName;
    }

    public void setOwedByLastName(String owedByLastName) {
        this.owedByLastName = owedByLastName;
    }

    public Integer getOwedTo() {
        return owedTo;
    }

    public void setOwedTo(Integer owedTo) {
        this.owedTo = owedTo;
    }

    public String getOwedToName() {
        return owedToName;
    }

    public void setOwedToName(String owedToName) {
        this.owedToName = owedToName;
    }

    public String getOwedToLastName() {
        return owedToLastName;
    }

    public void setOwedToLastName(String owedToLastName) {
        this.owedToLastName = owedToLastName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getSplitID() {
        return splitID;
    }

    public void setSplitID(Integer splitID) {
        this.splitID = splitID;
    }
}

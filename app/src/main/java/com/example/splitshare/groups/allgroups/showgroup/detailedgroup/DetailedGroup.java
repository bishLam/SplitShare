package com.example.splitshare.groups.allgroups.showgroup.detailedgroup;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import java.io.Serializable;
import java.util.Date;

public class DetailedGroup implements Serializable {
    @ColumnInfo(name = "GROUP_ID")
    private Integer groupID;
    @ColumnInfo(name = "GROUP_NAME")
    private String groupName;
    @ColumnInfo(name = "GROUP_DESCRIPTION")
    private String groupDescription;
    @ColumnInfo(name = "LAST_RECEIPT")
    private Date lastReceipt;
    //    @ColumnInfo(name = "CURRENT_STATE")
//    private String currentState;
    @ColumnInfo(name = "TOTAL_MEMBERS")
    private Integer totalMembers;

    @Ignore
    public DetailedGroup() {
    }

    public DetailedGroup(Integer groupID, String groupName, Date lastReceipt, String groupDescription, Integer totalMembers) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.lastReceipt = lastReceipt;
//        this.currentState = currentState;
        this.totalMembers = totalMembers;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Date getLastReceipt() {
        return lastReceipt;
    }

    public void setLastReceipt(Date lastReceipt) {
        this.lastReceipt = lastReceipt;
    }
//
//    public String getCurrentState() {
//        return currentState;
//    }
//
//    public void setCurrentState(String currentState) {
//        this.currentState = currentState;
//    }

    public Integer getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }
}

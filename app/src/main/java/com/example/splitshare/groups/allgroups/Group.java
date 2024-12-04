package com.example.splitshare.groups.allgroups;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import kotlin.jvm.Transient;

@Entity(tableName = "GROUPS")
public class Group implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "GROUP_ID")
    private Integer groupID;

    @ColumnInfo(name = "GROUP_NAME")
    private String groupName;

    @ColumnInfo(name = "GROUP_DESCRIPTION")
    private String groupDescription;

//    @ColumnInfo(name = "TOTAL_RECEIPTS")
//    private Integer totalReceipts;
//
//    @ColumnInfo(name = "LAST_RECEIPT")
//    private Date lastReceipt;
//
//    @ColumnInfo(name = "CURRENT_STATE")
//    private String currentState;

//    @Ignore
//    private Integer totalMembers;

    @Ignore
    public Group() {
    }

    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
//        this.totalReceipts = totalReceipts;
//        this.lastReceipt = lastReceipt;
//        this.currentState = currentState;
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

//    public Integer getTotalReceipts() {
//        return totalReceipts;
//    }
//
//    public void setTotalReceipts(Integer totalReceipts) {
//        this.totalReceipts = totalReceipts;
//    }
//
//    public Date getLastReceipt() {
//        return lastReceipt;
//    }
//
//    public void setLastReceipt(Date lastReceipt) {
//        this.lastReceipt = lastReceipt;
//    }
//
//    public String getCurrentState() {
//        return currentState;
//    }
//
//    public void setCurrentState(String currentState) {
//        this.currentState = currentState;
//    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
//                ", totalReceipts=" + totalReceipts +
//                ", lastReceipt=" + lastReceipt +
                '}';
    }

//    public Integer getTotalMembers() {
//        return totalMembers;
//    }
//
//    public void setTotalMembers(Integer totalMembers) {
//        this.totalMembers = totalMembers;
//    }
}

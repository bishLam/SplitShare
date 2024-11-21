package com.example.splitshare.composite_tables;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.User;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "USER_GROUP",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "USER_ID",
                        childColumns = "USER_ID",
                        onDelete = CASCADE
                ),
                @ForeignKey(entity = Group.class,
                        parentColumns = "GROUP_ID",
                        childColumns = "GROUP_ID",
                        onDelete = CASCADE
                )
        },
        indices = {
                @Index(value = "USER_ID"),
                @Index(value = "GROUP_ID"),
                @Index(value = {"USER_ID", "GROUP_ID"}, unique = true)
        },
        primaryKeys = {
                ("USER_ID")
                ,
                ("GROUP_ID")}
)
public class UserGroup {
    @NotNull
    @ColumnInfo(name = "USER_ID")
    private Integer userID;

    @NotNull
    @ColumnInfo(name = "GROUP_ID")
    private Integer groupID;

    @ColumnInfo(name = "TOTAL_SPENDING")
    private Double totalSpending;

    @ColumnInfo(name = "TOTAL_OWED")
    private Double totalOwed;

    @Ignore
    public UserGroup() {
    }

    public UserGroup(Integer userID, Integer groupID, Double totalSpending, Double totalOwed) {
        this.userID = userID;
        this.groupID = groupID;
        this.totalSpending = totalSpending;
        this.totalOwed = totalOwed;
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

    public Double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(Double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public Double getTotalOwed() {
        return totalOwed;
    }

    public void setTotalOwed(Double totalOwed) {
        this.totalOwed = totalOwed;
    }
}

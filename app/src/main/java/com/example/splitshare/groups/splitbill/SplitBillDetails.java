package com.example.splitshare.groups.splitbill;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.login.user.User;

import java.util.Date;

@Entity(tableName = "SPLIT_BILL_DETAILS",
        indices = {
                @Index(value = "SPLIT_ID"),
                @Index(value = "RECEIPT_ID"),
                @Index(value = "SPENDER_USER_ID"),
                @Index(value = "RECEIVER_USER_ID"),
                @Index(value = "GROUP_ID")
        },
        foreignKeys = {
                @ForeignKey(entity = Receipt.class, parentColumns = "RECEIPT_ID", childColumns = "RECEIPT_ID", onDelete = CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "USER_ID", childColumns = "SPENDER_USER_ID", onDelete = CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "USER_ID", childColumns = "RECEIVER_USER_ID", onDelete = CASCADE),
                @ForeignKey(entity = Group.class, parentColumns = "GROUP_ID", childColumns = "GROUP_ID", onDelete = CASCADE)
        }
)
public class SplitBillDetails {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SPLIT_ID")
    private Integer splitId;
    @ColumnInfo(name = "TOTAL_AMOUNT")
    private Double totalAmount;
    @ColumnInfo(name = "SPLITTED_AMOUNT")
    private Double splittedAmount;
    @ColumnInfo(name = "DESCRIPTION")
    private String description;
    @ColumnInfo(name = "RECEIPT_ID")
    private Integer receiptID;
    @ColumnInfo(name = "SPENDER_USER_ID")
    private Integer spenderUserID;
    @ColumnInfo(name = "RECEIVER_USER_ID")
    private Integer receiverUserID;
    @ColumnInfo(name = "GROUP_ID")
    private Integer groupID;
    @ColumnInfo(name = "DATE")
    private Date date;
    @ColumnInfo(name = "STATUS")
    private String status;

    @Ignore
    public SplitBillDetails() {
    }

    public SplitBillDetails(Double totalAmount, Double splittedAmount, String description, Integer receiptID, Integer spenderUserID, Integer receiverUserID, Integer groupID, Date date, String status) {
        this.totalAmount = totalAmount;
        this.splittedAmount = splittedAmount;
        this.description = description;
        this.receiptID = receiptID;
        this.spenderUserID = spenderUserID;
        this.receiverUserID = receiverUserID;
        this.groupID = groupID;
        this.date = date;
        this.status = status;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public void setSplitId(Integer splitId) {
        this.splitId = splitId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getSplittedAmount() {
        return splittedAmount;
    }

    public void setSplittedAmount(Double splittedAmount) {
        this.splittedAmount = splittedAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Integer receiptID) {
        this.receiptID = receiptID;
    }

    public Integer getSpenderUserID() {
        return spenderUserID;
    }

    public void setSpenderUserID(Integer spenderUserID) {
        this.spenderUserID = spenderUserID;
    }

    public Integer getReceiverUserID() {
        return receiverUserID;
    }

    public void setReceiverUserID(Integer receiverUserID) {
        this.receiverUserID = receiverUserID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SplitBillDetails{" +
                "splitId=" + splitId +
                ", totalAmount=" + totalAmount +
                ", splittedAmount=" + splittedAmount +
                ", description='" + description + '\'' +
                ", receiptID=" + receiptID +
                ", spenderUserID=" + spenderUserID +
                ", receiverUserID=" + receiverUserID +
                ", groupID=" + groupID +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}

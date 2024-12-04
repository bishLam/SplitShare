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
                @Index(value = "RECEIVER_USER_ID"),
        },
        foreignKeys = {
                @ForeignKey(entity = Receipt.class, parentColumns = "RECEIPT_ID", childColumns = "RECEIPT_ID", onDelete = CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "USER_ID", childColumns = "RECEIVER_USER_ID", onDelete = CASCADE),

        }
)
public class SplitBillDetails {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SPLIT_ID")
    private Integer splitId;
    @ColumnInfo(name = "SPLITTED_AMOUNT")
    private Double splittedAmount;
    @ColumnInfo(name = "RECEIPT_ID")
    private Integer receiptID;
    @ColumnInfo(name = "RECEIVER_USER_ID")
    private Integer receiverUserID;
    @ColumnInfo(name = "STATUS")
    private String status;

    @Ignore
    public SplitBillDetails() {
    }

    public SplitBillDetails(Double splittedAmount, Integer receiptID, Integer receiverUserID, String status) {
        this.splittedAmount = splittedAmount;
        this.receiptID = receiptID;
        this.receiverUserID = receiverUserID;
        this.status = status;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public void setSplitId(Integer splitId) {
        this.splitId = splitId;
    }

    public Double getSplittedAmount() {
        return splittedAmount;
    }

    public void setSplittedAmount(Double splittedAmount) {
        this.splittedAmount = splittedAmount;
    }

    public Integer getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Integer receiptID) {
        this.receiptID = receiptID;
    }

    public Integer getReceiverUserID() {
        return receiverUserID;
    }

    public void setReceiverUserID(Integer receiverUserID) {
        this.receiverUserID = receiverUserID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

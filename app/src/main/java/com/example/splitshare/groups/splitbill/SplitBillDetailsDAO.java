package com.example.splitshare.groups.splitbill;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SplitBillDetailsDAO {
    @Insert
    void insert(SplitBillDetails splitBill);

    @Update
    void update(SplitBillDetails splitBill);

    @Delete
    void delete(SplitBillDetails splitBill);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE SPLIT_ID = :id")
    SplitBillDetails getSplitBillByID(Integer id);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE RECEIPT_ID = :id")
    SplitBillDetails getSplitBillByReceiptID(Integer id);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE SPENDER_USER_ID = :id")
    SplitBillDetails getSplitBillBySpenderUserID(Integer id);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE RECEIVER_USER_ID = :id")
    SplitBillDetails getSplitBillByReceiverUserID(Integer id);


}

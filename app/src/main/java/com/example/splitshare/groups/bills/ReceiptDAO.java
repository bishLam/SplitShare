package com.example.splitshare.groups.bills;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.splitshare.activity.Activity;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;

import java.util.List;

@Dao
public interface ReceiptDAO {

    @Insert
    Long insert(Receipt receipt);

    @Update
    void update(Receipt receipt);

    @Delete
    void delete(Receipt receipt);

    @Query("SELECT R.USER_ID, R.RECEIPT_ID, R.RECEIPT_DESCRIPTION, R.RECEIPT_DATE, R.RECEIPT_AMOUNT, U.FIRST_NAME, U.LAST_NAME FROM RECEIPT AS R INNER JOIN USER AS U ON R.USER_ID = U.USER_ID WHERE R.GROUP_ID = :groupID")
    LiveData<List<DisplayReceiptClass>> getAllReceiptsByGroup(Integer groupID);

    @Query("SELECT * FROM RECEIPT WHERE USER_ID = :userID")
    LiveData<List<Receipt>> getAllReceiptsByUser(Integer userID);

    @Query("SELECT COUNT(RECEIPT_ID) AS TOTAL FROM RECEIPT WHERE GROUP_ID = :groupID")
    Long getTotalReceipts(Integer groupID);

    @Query("SELECT  R.USER_ID, R.RECEIPT_ID, R.RECEIPT_DESCRIPTION, R.RECEIPT_DATE, R.RECEIPT_AMOUNT, U.FIRST_NAME, U.LAST_NAME FROM RECEIPT AS R INNER JOIN USER AS U ON R.USER_ID = U.USER_ID WHERE R.GROUP_ID = :groupID ORDER BY R.RECEIPT_DATE DESC LIMIT 3")
    LiveData<List<DisplayReceiptClass>> getRecentReceipts(Integer groupID);

    @Query("SELECT SBD.SPLIT_ID AS ACTIVITY_ID,  R.RECEIPT_ID AS RECEIPT_ID, R.RECEIPT_DESCRIPTION AS RECEIPT_DESCRIPTION, R.RECEIPT_DATE AS RECEIPT_DATE, R.GROUP_ID AS GROUP_ID, R.RECEIPT_AMOUNT AS RECEIPT_AMOUNT, US.FIRST_NAME AS SPENDER_FIRST_NAME,US.LAST_NAME AS SPENDER_LAST_NAME, SBD.SPLITTED_AMOUNT AS SPLITTED_AMOUNT, SBD.STATUS AS STATUS, G.GROUP_NAME AS GROUP_NAME FROM RECEIPT AS R INNER JOIN USER AS US ON R.USER_ID = US.USER_ID INNER JOIN SPLIT_BILL_DETAILS AS SBD ON R.RECEIPT_ID = SBD.RECEIPT_ID INNER JOIN GROUPS AS G ON R.GROUP_ID = G.GROUP_ID WHERE SBD.RECEIVER_USER_ID = :userID")
    LiveData<List<Activity>> getActivityByUser(Integer userID);
}


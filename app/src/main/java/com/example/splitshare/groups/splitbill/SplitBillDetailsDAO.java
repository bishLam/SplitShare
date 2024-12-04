package com.example.splitshare.groups.splitbill;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.splitshare.groups.bills.settle.owes.OwesByAndTo;

@Dao
public interface SplitBillDetailsDAO {
    @Insert
    Long insert(SplitBillDetails splitBill);

    @Update
    void update(SplitBillDetails splitBill);

    @Delete
    void delete(SplitBillDetails splitBill);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE SPLIT_ID = :id")
    SplitBillDetails getSplitBillByID(Integer id);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE RECEIPT_ID = :id")
    SplitBillDetails getSplitBillByReceiptID(Integer id);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS AS S INNER JOIN RECEIPT AS R ON R.RECEIPT_ID = S.RECEIPT_ID WHERE R.USER_ID = :id")
    SplitBillDetails getSplitBillBySpenderUserID(Integer id);

    @Query("SELECT * FROM SPLIT_BILL_DETAILS WHERE RECEIVER_USER_ID = :id")
    SplitBillDetails getSplitBillByReceiverUserID(Integer id);

    @Query("SELECT SUM(SPLITTED_AMOUNT) AS AMOUNT, S.SPLIT_ID AS SPLIT_ID, R.USER_ID AS OWED_BY_UID , OBY.FIRST_NAME AS OWED_BY_NAME, OBY.LAST_NAME AS OWED_BY_LAST_NAME, R.USER_ID AS OWED_TO_UID, OTO.FIRST_NAME AS OWED_TO_NAME, OTO.LAST_NAME AS OWED_TO_LAST_NAME\n" +
            "FROM SPLIT_BILL_DETAILS AS S \n" +
            "INNER JOIN RECEIPT AS R ON S.RECEIPT_ID = R.RECEIPT_ID\n" +
            "INNER JOIN USER AS OBY ON S.RECEIVER_USER_ID = OBY.USER_ID\n" +
            "INNER JOIN USER AS OTO ON R.USER_ID = OTO.USER_ID \n" +
            "WHERE S.RECEIVER_USER_ID  =:owedByUID AND R.USER_ID =:owedToUID AND R.GROUP_ID = :groupID AND S.STATUS = 'assigned'")
    OwesByAndTo getOwedMoneyByUserToUser(Integer owedByUID, Integer owedToUID, Integer groupID);


    @Query("UPDATE SPLIT_BILL_DETAILS SET STATUS = 'settled' WHERE RECEIVER_USER_ID = :userID AND RECEIPT_ID IN (SELECT RECEIPT_ID FROM RECEIPT WHERE GROUP_ID = :groupID)")
    void settleBillByUserToGroup(Integer userID, Integer groupID);

    @Query("UPDATE SPLIT_BILL_DETAILS SET STATUS = 'settled' WHERE RECEIVER_USER_ID = :settledBy AND RECEIPT_ID IN (SELECT RECEIPT_ID FROM RECEIPT WHERE GROUP_ID = :groupID AND USER_ID = :settledTo)")
    void settleBillByUserToUser(Integer settledBy, Integer groupID, Integer settledTo);
}




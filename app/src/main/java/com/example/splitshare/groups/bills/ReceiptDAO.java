package com.example.splitshare.groups.bills;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReceiptDAO {

    @Insert
    Long insert(Receipt receipt);

    @Update
    void update(Receipt receipt);

    @Delete
    void delete(Receipt receipt);

    @Query("SELECT * FROM RECEIPT WHERE GROUP_ID = :groupID")
    LiveData <List<Receipt>> getAllReceiptsByGroup(Integer groupID);

    @Query("SELECT * FROM RECEIPT WHERE USER_ID = :userID")
    LiveData <List<Receipt>> getAllReceiptsByUser(Integer userID);
}

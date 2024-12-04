package com.example.splitshare.login.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM USER WHERE USER_ID = :id")
    User findByID(Integer id);

    @Query("SELECT * FROM USER WHERE UPPER(EMAIL) = UPPER(:email)")
    User findByEmail(String email);

    @Query("UPDATE USER SET FIRST_NAME = :firstName, LAST_NAME = :lastName, EMAIL = :email, PASSWORD = :password, PHONE_NUMBER = :phoneNumber WHERE USER_ID = :id")
    int updateUserByUID(Integer id, String firstName, String lastName, String email, String password, String phoneNumber);

    @Query("SELECT FIRST_NAME FROM USER WHERE USER_ID = :UID")
    String getUserNameFromUID(Integer UID);
}

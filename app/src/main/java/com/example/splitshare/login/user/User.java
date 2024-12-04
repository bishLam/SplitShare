package com.example.splitshare.login.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "USER",
        indices = {
                @Index(value = "EMAIL", unique = true)}
)
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "USER_ID")
    private Integer userID;
    @ColumnInfo(name = "FIRST_NAME")
    private String firstName;
    @ColumnInfo(name = "LAST_NAME")
    private String lastName;
    @ColumnInfo(name = "PASSWORD")
    private String password;
    @ColumnInfo(name = "EMAIL")
    private String email;
    @ColumnInfo(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Ignore
    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

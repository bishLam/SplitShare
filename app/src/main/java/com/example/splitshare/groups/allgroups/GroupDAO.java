package com.example.splitshare.groups.allgroups;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GroupDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long insert(Group group);

    @Delete
    void delete(Group group);

    @Update
    void update(Group group);

    @Query("SELECT * FROM GROUPS WHERE GROUP_ID = :groupID")
    Group findByGroupId(Integer groupID);

    @Query("SELECT * FROM GROUPS WHERE GROUP_NAME = :groupName")
    Group findGroupByGroupName(String groupName);


    @Query("SELECT COUNT(USER_ID) FROM USER_GROUP WHERE GROUP_ID = :groupID")
    Integer findTotalMembers(Integer groupID);

}

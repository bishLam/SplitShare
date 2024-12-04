package com.example.splitshare.composite_tables;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.login.user.User;

import java.util.List;

@Dao
public interface UserGroupDAO {

    @Insert
    Long insert(UserGroup userGroup);

    @Update
    void update(UserGroup userGroup);

    @Delete
    void delete(UserGroup userGroup);

    @Query("SELECT g.*, (SELECT COUNT(USER_ID) FROM USER_GROUP as ug WHERE GROUP_ID = g.GROUP_ID) AS TotalMembers FROM GROUPS as g INNER JOIN USER_GROUP as ug ON g.GROUP_ID = ug.GROUP_ID WHERE ug.USER_ID = :id")
    LiveData<List<Group>> getGroupsByUserID(Integer id);

    @Query("SELECT U.* FROM USER as U INNER JOIN USER_GROUP as ug ON U.USER_ID = ug.USER_ID WHERE ug.GROUP_ID = :id")
    LiveData<List<User>> getUsersByGroupID(Integer id);


    @Query("SELECT  UG.* FROM USER_GROUP AS UG INNER JOIN GROUPS G ON G.GROUP_ID = UG.GROUP_ID  WHERE G.GROUP_NAME = :groupName AND UG.USER_ID = :UID;")
    UserGroup findGroupByGroupNameAndUID(String groupName, Integer UID);

    @Query("SELECT COUNT(USER_ID) FROM USER_GROUP WHERE GROUP_ID = :groupID")
    Integer getTotalMembers(Integer groupID);

    @Query("SELECT * FROM USER_GROUP WHERE USER_ID = :userID AND GROUP_ID = :groupID")
    UserGroup findByUserIDAndGroupID(Integer userID, Integer groupID);

    @Query("SELECT g.GROUP_ID AS GROUP_ID,  g.GROUP_NAME AS GROUP_NAME, g.GROUP_DESCRIPTION AS GROUP_DESCRIPTION, (SELECT COUNT(USER_ID) FROM USER_GROUP as ug WHERE ug.GROUP_ID = g.GROUP_ID) AS TOTAL_MEMBERS, (SELECT R.RECEIPT_DATE FROM RECEIPT AS r WHERE r.GROUP_ID = g.GROUP_ID ORDER BY RECEIPT_DATE DESC LIMIT 1 )AS LAST_RECEIPT FROM GROUPS as g INNER JOIN USER_GROUP as ug ON g.GROUP_ID = ug.GROUP_ID WHERE ug.USER_ID = :id")
    LiveData<List<DetailedGroup>> getDetailedGroup(Integer id);

    @Query("SELECT USER_ID FROM USER_GROUP WHERE GROUP_ID = :groupID")
    List<Integer> getUsersInAGroup(Integer groupID);

}

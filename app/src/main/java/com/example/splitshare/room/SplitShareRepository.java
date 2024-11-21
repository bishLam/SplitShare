package com.example.splitshare.room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.composite_tables.UserGroupDAO;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.GroupDAO;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.groups.splitbill.SplitBillDetailsDAO;
import com.example.splitshare.login.user.User;
import com.example.splitshare.login.user.UserDAO;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.bills.ReceiptDAO;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SplitShareRepository {
    //DAO's section

    private final UserDAO userDAO;
    private final GroupDAO groupDAO;
    private final UserGroupDAO userGroupDAO;
    private final ReceiptDAO receiptDAO;
    private final SplitBillDetailsDAO splitBillDAO;

    private User user;
    private Group group;
    private UserGroup userGroup;
    private Integer totalMembers;
    private LiveData<List<Group>> groupsByUID;

    public SplitShareRepository(Application application) {
        SplitShareRoomDatabase splitShareRoomDatabase = SplitShareRoomDatabase.getDatabase(application);
        userDAO = splitShareRoomDatabase.userDAO();
        groupDAO = splitShareRoomDatabase.groupDAO();
        userGroupDAO = splitShareRoomDatabase.userGroupDAO();
        receiptDAO = splitShareRoomDatabase.receiptDAO();
        splitBillDAO = splitShareRoomDatabase.splitBillDAO();
    }



    //METHODS FROM USERS TABLE
    public Long insert(User user) {
        Callable<Long> c = () -> {
            return userDAO.insert(user);
        };

        Future<Long> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(c);
        Long id;
        try {
            id = future.get();
            return id;
        } catch (ExecutionException | InterruptedException e) {
            Log.d("Exception", "Exception");
            return null;
        }
    }

    public void update(User user) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() ->
        {
            userDAO.update(user);
        });
    }

    public void delete(User user) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            userDAO.delete(user);

        });
    }

    public User findUserByEmail(String email) throws ExecutionException, InterruptedException {
        Callable<User> callable = () -> {
            return userDAO.findByEmail(email);
        };

        Future<User> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(callable);
        try {
            user = future.get();
        } catch (ExecutionException | InterruptedException e) {
            user = null;
        }

        return user;
    }

    public User findUserByID(Integer id) throws ExecutionException, InterruptedException {
        Callable<User> callable = () -> {
            return userDAO.findByID(id);
        };

        Future<User> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(callable);
        try {
            user = future.get();
        } catch (ExecutionException | InterruptedException e) {

        }
        return user;
    }

    public long updateUserByUID(Integer id, String firstName, String lastName, String email, String password, String phoneNumber){
        long ststusCode;
        Callable<Long> callable = () -> {
            int status = userDAO.updateUserByUID(id, firstName, lastName, email, password, phoneNumber);
            return (long) status;
        };

        Future<Long> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(callable);
        try {
            ststusCode = future.get();
        } catch (ExecutionException | InterruptedException e) {
            ststusCode = 0L;
        }
        return ststusCode;
    }




    //METHODS FROM GROUPS TABLE
    public Long insert(Group group) throws ExecutionException, InterruptedException {
        Callable<Long> c = () -> {
            return groupDAO.insert(group);
        };

        Future<Long> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(c);
        Long id;
        try {
            id = future.get();
            return id;
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }

    }

    public void update(Group group) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            groupDAO.update(group);
        });
    }

    public void delete(Group group) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            groupDAO.delete(group);
        });
    }

    public Group findGroupByID(Integer id) {
        Callable<Group> callable = () -> {
            return groupDAO.findByGroupId(id);
        };

        Future<Group> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(callable);
        try {
            group = future.get();
        } catch (ExecutionException | InterruptedException e) {
        }
        return group;
    }






    //METHODS FROM USER_GROUP TABLE
    public Long insert(UserGroup usergroup) {
        Callable<Long> c = () -> {
            return userGroupDAO.insert(usergroup);
        };

        Future<Long> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(c);
        Long id;
        try {
            id = future.get();
            return id;
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    public void update(UserGroup usergroup) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            userGroupDAO.update(usergroup);
        });
    }

    public void delete(UserGroup usergroup) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            userGroupDAO.delete(usergroup);
        });
    }

    public LiveData<List<Group>> getGroupsByUserID(Integer id) {
        return userGroupDAO.getGroupsByUserID(id);
    }

    public LiveData<List<User>> getUsersByGroupID(Integer id) {
        return userGroupDAO.getUsersByGroupID(id);
    }

    public UserGroup findGroupByNameAndUID(String groupName, int UID) {
        Callable<UserGroup> callable = () -> {
            return userGroupDAO.findGroupByGroupNameAndUID(groupName, UID);
        };

        Future<UserGroup> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(callable);
        try {
            userGroup = future.get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e("Exception", "Exception");
        }

        return userGroup;
    }

    public Integer getTotalMembers(Integer groupID) throws ExecutionException, InterruptedException {

        Callable <Integer> c = ()->{
            return userGroupDAO.getTotalMembers(groupID);
        };

        Future<Integer> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(c);
        return future.get();
    };

    public UserGroup findByUserIDAndGroupID(Integer userID, Integer groupID) throws ExecutionException, InterruptedException {

        Callable<UserGroup> callable = () -> {
            return userGroupDAO.findByUserIDAndGroupID(userID, groupID);
        };

        Future<UserGroup> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(callable);
       return future.get();
    }

    public LiveData<List<DetailedGroup>> getDetailedGroup(Integer id) {
        return userGroupDAO.getDetailedGroup(id);
    }






    //METHOD FOR RECEIPTS TABLE
    public Long insert(Receipt receipt) {
        Callable<Long> c = () -> {
            return receiptDAO.insert(receipt);
        };
        Future<Long> future = SplitShareRoomDatabase.DatabaseWriteExecutor.submit(c);
        Long id;

        try {
            id = future.get();
            return id;
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    public void update(Receipt receipt) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            receiptDAO.update(receipt);
        });
    }

    public void delete(Receipt receipt) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {
            receiptDAO.delete(receipt);
        });
    }

    public LiveData<List<Receipt>> getAllReceiptsByGroup(Integer groupID) {
        return receiptDAO.getAllReceiptsByGroup(groupID);
    }

    public LiveData<List<Receipt>> getAllReceiptsByUser(Integer userID) {
        return receiptDAO.getAllReceiptsByUser(userID);
    }






    //METHODS FROM SPLIT_BILL_DETAILS_DAO TABLE





}

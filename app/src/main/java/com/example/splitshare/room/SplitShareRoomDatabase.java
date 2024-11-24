package com.example.splitshare.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.composite_tables.UserGroupDAO;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.GroupDAO;
import com.example.splitshare.groups.splitbill.SplitBillDetails;
import com.example.splitshare.groups.splitbill.SplitBillDetailsDAO;
import com.example.splitshare.login.user.User;
import com.example.splitshare.login.user.UserDAO;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.bills.ReceiptDAO;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Group.class, User.class, UserGroup.class, Receipt.class, SplitBillDetails.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class SplitShareRoomDatabase extends RoomDatabase {
    //DAO's section
    public abstract UserDAO userDAO();

    public abstract GroupDAO groupDAO();

    public abstract UserGroupDAO userGroupDAO();

    public abstract ReceiptDAO receiptDAO();

    public abstract SplitBillDetailsDAO splitBillDAO();

    public static volatile SplitShareRoomDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService DatabaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static SplitShareRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), SplitShareRoomDatabase.class, "SplitShare_Room_Database")
                    .addCallback(roomCallBack)
                    .fallbackToDestructiveMigration()
                    .build();
            INSTANCE.query("SELECT 1", null);
        }
        return INSTANCE;
    }


    public static Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populateInitialData(INSTANCE);
            Log.i("XYZ", "onCreate Called");
        }


        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.i("XYZ", "onOpen Called");
        }
    };

    public static void populateInitialData(SplitShareRoomDatabase INSTANCE) {
        SplitShareRoomDatabase.DatabaseWriteExecutor.execute(() -> {

            //insert user data
            UserDAO userDAO = INSTANCE.userDAO();
            userDAO.insert(new User("admin", "admin", "admin@ait.edu.au", "1234", "1234"));
            User user1 = userDAO.findByEmail("admin@ait.edu.au");
            userDAO.insert(new User("test", "test", "test@ait.edu.au", "1234", "1234"));
            User user2 = userDAO.findByEmail("test@ait.edu.au");
            userDAO.insert(new User("user", "user", "user@ait.edu.au", "1234", "1234"));
            User user3 = userDAO.findByEmail("user@ait.edu.au");


            //create group and insert two users
            GroupDAO groupDAO = INSTANCE.groupDAO();
            Group group1 = new Group("Homies", "Groceries and bills", 0, new Date(2000 - 1 - 1), "Active");
            groupDAO.insert(group1);
            group1 = groupDAO.findGroupByGroupName("Homies");
            Group group2 = new Group("Sports Group", "Sports accessories and events", 0, new Date(2000 - 1 - 1), "Active");
            groupDAO.insert(group2);
            group2 = groupDAO.findGroupByGroupName("Sports Group");

            groupDAO.findByGroupId(group1.getGroupID());
            groupDAO.findByGroupId(group2.getGroupID());


            //UserGroup class and DAO's
            UserGroupDAO userGroupDAO = INSTANCE.userGroupDAO();
            UserGroup userGroup1 = new UserGroup(user1.getUserID(), group1.getGroupID(), 0.0, 0.0);
            userGroupDAO.insert(userGroup1);
            UserGroup userGroup2 = new UserGroup(user2.getUserID(), group1.getGroupID(), 0.0, 0.0);
            userGroupDAO.insert(userGroup2);
            UserGroup userGroup3 = new UserGroup(user3.getUserID(), group2.getGroupID(), 0.0, 0.0);
            userGroupDAO.insert(userGroup3);

            userGroupDAO.getGroupsByUserID(user1.getUserID());
            userGroupDAO.getUsersByGroupID(groupDAO.findByGroupId(1).getGroupID());

            //receipts group and DAO's
            ReceiptDAO receiptDAO = INSTANCE.receiptDAO();
            Receipt receipt1 = new Receipt("Electricity Bill", 100.0, new Date(2000 - 1 - 1), user1.getUserID(), group1.getGroupID());
            receiptDAO.insert(receipt1);
            Receipt receipt2 = new Receipt("Gas bill", 50.0, new Date(2000 - 1 - 1), user2.getUserID(), group1.getGroupID());
            receiptDAO.insert(receipt2);
            Receipt receipt3 = new Receipt("Test Bill2", 100.0, new Date(2000 - 1 - 1), user1.getUserID(), group2.getGroupID());
            receiptDAO.insert(receipt3);
            Receipt receipt4 = new Receipt("Test bill2", 50.0, new Date(2000 - 01 - 01), user2.getUserID(), group2.getGroupID());
            receiptDAO.insert(receipt4);
            receiptDAO.getAllReceiptsByGroup(groupDAO.findByGroupId(1).getGroupID());
            receiptDAO.getAllReceiptsByUser(user1.getUserID());

            //splitbill
            SplitBillDetailsDAO splitBillDAO = INSTANCE.splitBillDAO();
            SplitBillDetails splitBillDetails1 = new SplitBillDetails(50.00, 1, user2.getUserID(), "assigned");
            splitBillDAO.insert(splitBillDetails1);
            SplitBillDetails splitBillDetails2 = new SplitBillDetails(50.00, 1, user1.getUserID(), "assigned");
            splitBillDAO.insert(splitBillDetails2);
            SplitBillDetails splitBillDetails3 = new SplitBillDetails(100.00, 2, user1.getUserID(), "assigned");
            splitBillDAO.insert(splitBillDetails3);
            SplitBillDetails splitBillDetails4 = new SplitBillDetails(100.00, 2, user2.getUserID(), "assigned");
            splitBillDAO.insert(splitBillDetails4);
        });
    }
}

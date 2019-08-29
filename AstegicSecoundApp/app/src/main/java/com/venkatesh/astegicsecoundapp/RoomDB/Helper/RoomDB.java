package com.venkatesh.astegicsecoundapp.RoomDB.Helper;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import com.venkatesh.astegicsecoundapp.RoomDB.AccountsDataRecords;
import com.venkatesh.astegicsecoundapp.RoomDB.ContactsDataRecord;
import com.venkatesh.astegicsecoundapp.RoomDB.ExtensionsDataRecord;
import com.venkatesh.astegicsecoundapp.RoomDB.JoinDataRecord;


/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * This will be the class that will give us access to the implementation of the DAO interface.
 */

@Database(entities = {ContactsDataRecord.class, AccountsDataRecords.class, ExtensionsDataRecord.class, JoinDataRecord.class}, version = 2, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile RoomDB roomDB;
    private Context mContext;

    /**
     * This API allows to create the database
     *
     * @param context : context : application context
     * @return : RoomDB : database instance
     */
    public static RoomDB initDatabase(final Context context) {

        if (roomDB == null) {
            synchronized (RoomDB.class) {
                if (roomDB == null) {

                    /*
                     Room.databaseBuilder() takes 3 arguments:

                      Reference to Context .
                      Abstract database class to return the instance of.
                      Name of the database.

                      */
                    roomDB = Room.databaseBuilder(context,
                            RoomDB.class, "AstegicDB")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            /*
                               allowMainThreadQueries :  by default Room doesn’t allow you to run queries on  MainThread ,
                               application give permission to use main thread for db operation
                               */
                            .allowMainThreadQueries()
                            //.addCallback(sRoomDatabaseCallback) //on comment this call back,if required
                            .build();
                }
            }

            /*
             This API call allows to write json data to database in AsyncTask.
              @input : RoomDb : RoomDb instance
             */
            roomDB.setContext(context);
            new PopulateDbAsyncTask(roomDB).execute();
        }
        return roomDB;
    }


    private void setContext(Context context) {
        mContext = context;
    }

    public abstract CvtDataAccessObject jsonDao();

    private void loadScreenData(CvtDataAccessObject jDao) {

        ContactsDataRecord[] contactsDataRecords = new ContactsDataRecord[3];
        contactsDataRecords[0] = new ContactsDataRecord(2, "48f3", "1196");
        contactsDataRecords[1] = new ContactsDataRecord(3, "3e47", "f1fe");
        contactsDataRecords[2] = new ContactsDataRecord(4, "2cac", "036e");

        jDao.insertContactsDataRecord(contactsDataRecords);

        ExtensionsDataRecord[] extensionsDataRecords = new ExtensionsDataRecord[3];
        extensionsDataRecords[0] = new ExtensionsDataRecord("Gmail", 2);
        extensionsDataRecords[1] = new ExtensionsDataRecord("Gmail", 3);
        extensionsDataRecords[2] = new ExtensionsDataRecord("Gmail1", 4);

        jDao.insertExtensionsDataRecord(extensionsDataRecords);

        AccountsDataRecords[] accountsDataRecords = new AccountsDataRecords[2];
        accountsDataRecords[0] = new AccountsDataRecords(1, "test_one@gmail.com", "Gmail");
        accountsDataRecords[1] = new AccountsDataRecords(0, "test_two@gmail.com", "Gmail1");
        jDao.insertAccountantRecord(accountsDataRecords);


    }

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private final CvtDataAccessObject jDao;
        private final RoomDB mRoomDB;

        PopulateDbAsyncTask(RoomDB db) {
            jDao = db.jsonDao();
            mRoomDB = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            jDao.deleteAccountsAll();
            jDao.deleteContactsAll();
            jDao.deleteExtensionsAll();
            mRoomDB.loadScreenData(jDao);
            return null;
        }
    }


}

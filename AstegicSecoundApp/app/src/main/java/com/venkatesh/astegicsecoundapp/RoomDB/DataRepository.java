package com.venkatesh.astegicsecoundapp.RoomDB;

import android.app.Application;
import android.os.AsyncTask;

import com.venkatesh.astegicsecoundapp.MainActivity;
import com.venkatesh.astegicsecoundapp.RoomDB.Helper.CvtDataAccessObject;
import com.venkatesh.astegicsecoundapp.RoomDB.Helper.RoomDB;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class DataRepository {

    private CvtDataAccessObject mCvtDataAccessObject;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
  public DataRepository(MainActivity application) {

      RoomDB db = RoomDB.initDatabase(application);
        mCvtDataAccessObject = db.jsonDao();
    }



    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
   public void insertAccountDetails(AccountsDataRecords... accountsDataRecords) {
        new insertAsyncTask(mCvtDataAccessObject).execute(accountsDataRecords); // You must call this on a non-UI thread or your app will crash.
    }

    public void insertContactDetails(ContactsDataRecord... contactsDataRecords) {
        new insertAsyncTask2(mCvtDataAccessObject).execute(contactsDataRecords); // You must call this on a non-UI thread or your app will crash.
    }
    public void insertExtensionDetails(ExtensionsDataRecord... extensionsDataRecords) {
        new insertAsyncTask1(mCvtDataAccessObject).execute(extensionsDataRecords); // You must call this on a non-UI thread or your app will crash.
    }

    public JoinDataRecord getScreenData(String screenName) {
        return mCvtDataAccessObject.findByName(screenName);
    }



    private static class insertAsyncTask extends AsyncTask<AccountsDataRecords, Void, Void> {

        private CvtDataAccessObject mAsyncTaskDao;

        insertAsyncTask(CvtDataAccessObject dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AccountsDataRecords... params) {
           // mAsyncTaskDao.insertAccountantRecord(params);
            return null;
        }
    }
    private static class insertAsyncTask1 extends AsyncTask<ExtensionsDataRecord, Void, Void> {

        private CvtDataAccessObject mAsyncTaskDao;

        insertAsyncTask1(CvtDataAccessObject dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ExtensionsDataRecord... params) {
            //mAsyncTaskDao.insertExtensionsDataRecord(params);
            return null;
        }
    }
    private static class insertAsyncTask2 extends AsyncTask<ContactsDataRecord, Void, Void> {

        private CvtDataAccessObject mAsyncTaskDao;

        insertAsyncTask2(CvtDataAccessObject dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ContactsDataRecord... params) {
           // mAsyncTaskDao.insertContactsDataRecord(params);
            return null;
        }
    }
}

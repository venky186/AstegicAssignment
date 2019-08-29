package com.venkatesh.astegicsecoundapp.RoomDB.Helper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.venkatesh.astegicsecoundapp.RoomDB.AccountsDataRecords;
import com.venkatesh.astegicsecoundapp.RoomDB.ContactsDataRecord;
import com.venkatesh.astegicsecoundapp.RoomDB.ExtensionsDataRecord;
import com.venkatesh.astegicsecoundapp.RoomDB.JoinDataRecord;

import java.util.List;


/**
 * DAO: Stands for Data Access Object.
 * It is an interface that defines all the operations that we need to perform in our database.
 */
@Dao
public interface CvtDataAccessObject {

    @Query("DELETE FROM AccountsDataRecords")
    void deleteAccountsAll();

    @Query("DELETE FROM ContactsDataRecord")
    void deleteContactsAll();

    @Query("DELETE FROM ExtensionsDataRecord")
    void deleteExtensionsAll();

    @Insert
    void insertAccountantRecord(AccountsDataRecords... accountsDataRecords);

    @Insert
    void insertContactsDataRecord(ContactsDataRecord... contactsDataRecord);

    @Insert
    void insertExtensionsDataRecord(ExtensionsDataRecord... extensionsDataRecord);

    @Insert
    void insertAccountantSingleRecord(AccountsDataRecords accountsDataRecords);


    @Insert
    void insertContactsSingleDataRecord(ContactsDataRecord contactsDataRecord);

    @Insert
    void insertExtensionsSingleDataRecord(ExtensionsDataRecord extensionsDataRecord);

    @Query("SELECT c.stagingId,b.context,a.userID,a.status\n" +
            "FROM ContactsDataRecord c,ExtensionsDataRecord b,AccountsDataRecords a where c.contactId like :screenName and c._id == b.phoneContactId and b.context == a.context;\n" +
            "\n")
    JoinDataRecord findByName(String screenName);

}

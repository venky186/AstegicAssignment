package com.venkatesh.astegicsecoundapp.RoomDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity//(foreignKeys = {@ForeignKey(entity = ContactsDataRecord.class, parentColumns = "_id", childColumns = "phoneContactId"),@ForeignKey(entity = AccountsDataRecords.class, parentColumns = "context", childColumns = "context")}, indices = {@Index(value = "phoneContactId"),@Index(value = "context")})
public class ExtensionsDataRecord {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;
    @ColumnInfo(name = "context")
    private String context;
    @ColumnInfo(name = "phoneContactId")
    private int phoneContactId;

    public ExtensionsDataRecord(String context , int phoneContactId) {

        this.context = context;
        this.phoneContactId = phoneContactId;

    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPhoneContactId() {
        return phoneContactId;
    }

    public void setPhoneContactId(int phoneContactId) {
        this.phoneContactId = phoneContactId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

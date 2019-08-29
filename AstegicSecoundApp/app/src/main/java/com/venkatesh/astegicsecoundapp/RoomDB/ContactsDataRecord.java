package com.venkatesh.astegicsecoundapp.RoomDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ContactsDataRecord {

    @PrimaryKey
    private int _id;
    @ColumnInfo(name = "contactId")
    private String contactId;
    @ColumnInfo(name = "stagingId")

    private String stagingId;

    public ContactsDataRecord(int _id, String contactId, String stagingId) {
        this._id = _id;
        this.contactId = contactId;
        this.stagingId = stagingId;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getStagingId() {
        return stagingId;
    }

    public void setStagingId(String stagingId) {
        this.stagingId = stagingId;
    }
}

package com.venkatesh.astegicsecoundapp.RoomDB;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity//(foreignKeys = @ForeignKey(entity = ExtensionsDataRecord.class, parentColumns = "context", childColumns = "context"))
public class AccountsDataRecords {


    @ColumnInfo(name = "status")
    private int status;
    @ColumnInfo(name = "userID")
    private String userID;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "context")
    private String context;

    public AccountsDataRecords(int status,String userID , String context){

        this.status = status;
        this.userID = userID;
        this.context=context;


    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

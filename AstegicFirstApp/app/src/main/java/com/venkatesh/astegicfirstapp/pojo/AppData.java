package com.venkatesh.astegicfirstapp.pojo;

import java.io.Serializable;

public class AppData implements Serializable {

    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mLink;
    private String mTags;
    private String mImage;

    public AppData(String mTitle, String mAuthor, String mAuthorId, String mLink, String mTags, String mImage) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAuthorId = mAuthorId;
        this.mLink = mLink;
        this.mTags = mTags;
        this.mImage = mImage;
    }



    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getLink() {
        return mLink;
    }

    public String getTags() {
        return mTags;
    }

    public String getImage() {
        return mImage;
    }
}

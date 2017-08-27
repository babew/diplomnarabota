package com.gradski.transport.varna.models;

/**
 * Created by Lyubomir Babev on 8/21/2017.
 */

public class News {
    private long    mId;
    private String  mTitle;
    private String  mMessage;
    private String  mDate;
    private String  mImageUrl;

    public News(long id, String title, String message, String date, String imageUrl) {
        this.mId        = id;
        this.mTitle     = title;
        this.mMessage   = message;
        this.mDate      = date;
        this.mImageUrl  = imageUrl;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getDate() {
        return mDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}

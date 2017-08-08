package com.gradski.transport.varna.models;

/**
 * Created by lyubomir.babev on 21/07/2017.
 */

public class RouteChange {
    private long    mId;
    private int mImageType;
    private String  mTitle;
    private String  mMessage;

    public RouteChange(long id, int imageType, String title, String message) {
        this.mId        = id;
        this.mImageType = imageType;
        this.mTitle     = title;
        this.mMessage   = message;
    }

    public long getId() {
        return mId;
    }

    public int getImageType() {
        return mImageType;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMessage() {
        return mMessage;
    }
}

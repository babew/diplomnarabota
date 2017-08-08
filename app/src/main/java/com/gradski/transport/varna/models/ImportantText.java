package com.gradski.transport.varna.models;

/**
 * Created by lyubomir.babev on 24/07/2017.
 */

public class ImportantText {
    private long    mId;
    private String  mText;
    private int     mType;

    public ImportantText(long id, String text, int type) {
        this.mId        = id;
        this.mText      = text;
        this.mType      = type;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public int getType() {
        return mType;
    }
}

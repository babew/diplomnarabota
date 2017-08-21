package com.gradski.transport.varna.models;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 16/08/2017.
 */

public class ImportantObject {
    private long                        mId;
    private String                      mTitle;
    private ArrayList<ImportantText>    mTexts;

    public ImportantObject(long id, String title, ArrayList<ImportantText> texts) {
        this.mId        = id;
        this.mTitle     = title;
        this.mTexts     = texts;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<ImportantText> getTexts() {
        return mTexts;
    }
}

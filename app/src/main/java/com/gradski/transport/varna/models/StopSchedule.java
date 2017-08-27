package com.gradski.transport.varna.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/21/2017.
 */

public class StopSchedule implements Parcelable {
    private long                mId;
    private String              mName;
    private ArrayList<String>   mStopTimes;

    public StopSchedule(long id, String name, ArrayList<String> stopTimes) {
        this.mId        = id;
        this.mName      = name;
        this.mStopTimes = stopTimes;
    }

    protected StopSchedule(Parcel in) {
        mId         = in.readLong();
        mName       = in.readString();
        mStopTimes  = in.createStringArrayList();
    }

    public static final Creator<StopSchedule> CREATOR = new Creator<StopSchedule>() {
        @Override
        public StopSchedule createFromParcel(Parcel in) {
            return new StopSchedule(in);
        }

        @Override
        public StopSchedule[] newArray(int size) {
            return new StopSchedule[size];
        }
    };

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<String> getStopTimes() {
        return mStopTimes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeStringList(mStopTimes);
    }
}

package com.gradski.transport.varna.models;

/**
 * Created by lyubomir.babev on 13/06/2017.
 */

public class Point {
    long    mId;
    String  mName;
    double  mLatitude;
    double  mLongitude;
    String  mAddress;
    String  mWeekdayWorkTime;
    String  mWeekdayBreakTime;
    String  mSaturdayWorkTime;
    String  mSaturdayBreakTime;
    String  mSundayWorkTime;
    String  mSundayBreakTime;

    public Point(long id, String name, double latitude, double longitude, String address, String weekdayWorkTime, String weekdayBreakTime, String saturdayWorkTime, String saturdayBreakTime, String sundayWorkTime, String sundayBreakTime) {
        this.mId                = id;
        this.mName              = name;
        this.mLatitude          = latitude;
        this.mLongitude         = longitude;
        this.mAddress           = address;
        this.mWeekdayWorkTime   = weekdayWorkTime;
        this.mWeekdayBreakTime  = weekdayBreakTime;
        this.mSaturdayWorkTime  = saturdayWorkTime;
        this.mSaturdayBreakTime = saturdayBreakTime;
        this.mSundayWorkTime    = sundayWorkTime;
        this.mSundayBreakTime   = sundayBreakTime;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getWeekdayWorkTime() {
        return mWeekdayWorkTime;
    }

    public String getWeekdayBreakTime() {
        return mWeekdayBreakTime;
    }

    public String getSaturdayWorkTime() {
        return mSaturdayWorkTime;
    }

    public String getSaturdayBreakTime() {
        return mSaturdayBreakTime;
    }

    public String getSundayWorkTime() {
        return mSundayWorkTime;
    }

    public String getSundayBreakTime() {
        return mSundayBreakTime;
    }

}

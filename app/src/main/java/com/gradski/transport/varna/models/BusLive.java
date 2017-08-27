package com.gradski.transport.varna.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/22/2017.
 */

public class BusLive extends Object {
    private long                mId;
    private int                 mNumber;
    private long                mLastStopId;
    private long                mNextStopId;
    private String              mTime;
    private String              mOffsetTime;
    private int                 mOffsetMeters;
    private LatLng              mLocation;
    private ArrayList<LatLng>   mRouteLocationsArrayList;
    private boolean             mBusStopped;
    private int                 mLastLocationId;

    public BusLive(long id, int number, long lastStopId, long nextStopId, String time, String offsetTime, int offsetMeters, LatLng location, boolean busStopped, int lastLocationId) {
        this.mId                        = id;
        this.mNumber                    = number;
        this.mLastStopId                = lastStopId;
        this.mNextStopId                = nextStopId;
        this.mTime                      = time;
        this.mOffsetTime                = offsetTime;
        this.mOffsetMeters              = offsetMeters;
        this.mLocation                  = location;
        this.mBusStopped                = busStopped;
        this.mLastLocationId            = lastLocationId;
    }

    public long getId() {
        return mId;
    }

    public int getNumber() {
        return mNumber;
    }

    public long getLastStopId() {
        return mLastStopId;
    }

    public long getNextStopIdId() {
        return mNextStopId;
    }

    public String getTime() {
        return mTime;
    }

    public String getOffsetTime() {
        return mOffsetTime;
    }

    public int getOffsetMeters() {
        return mOffsetMeters;
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public ArrayList<LatLng> getRouteLocationsArrayList() {
        return mRouteLocationsArrayList;
    }

    public int getLastLocationId() {
        return mLastLocationId;
    }

    public boolean isBusStopped() {
        return mBusStopped;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public void setNumber(int number) {
        this.mNumber = number;
    }

    public void setLastStopId(long mLastStopId) {
        this.mLastStopId = mLastStopId;
    }

    public void setNextStopId(long mNextStopId) {
        this.mNextStopId = mNextStopId;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public void setOffsetTime(String mOffsetTime) {
        this.mOffsetTime = mOffsetTime;
    }

    public void setOffsetMeters(int mOffsetMeters) {
        this.mOffsetMeters = mOffsetMeters;
    }

    public void setLocation(LatLng mLocation) {
        this.mLocation = mLocation;
    }

    public void setRouteLocationsArrayList(ArrayList<LatLng> mRouteLocationsArrayList) {
        this.mRouteLocationsArrayList = mRouteLocationsArrayList;
    }

    public void setBusStopped(boolean busStopped) {
        this.mBusStopped = busStopped;
    }

    public void setLastLocationId(int lastLocationId) {
        this.mLastLocationId = lastLocationId;
    }
}

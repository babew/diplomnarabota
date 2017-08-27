package com.gradski.transport.varna.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/22/2017.
 */

public class BusStop {
    private long                mId;
    private String              mName;
    private String              mNextBusLiveStopTime;
    private LatLng              mLocation;

    public BusStop(long id, String name, String nextBusLiveStopTime, LatLng location) {
        this.mId                    = id;
        this.mName                  = name;
        this.mNextBusLiveStopTime   = nextBusLiveStopTime;
        this.mLocation              = location;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getNextBusLiveStopTime() {
        return mNextBusLiveStopTime;
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setNextBusLiveStopTime(String mNextBusLiveStopTime) {
        this.mNextBusLiveStopTime = mNextBusLiveStopTime;
    }

    public void setLocation(LatLng mLocation) {
        this.mLocation = mLocation;
    }
}

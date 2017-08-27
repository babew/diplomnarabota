package com.gradski.transport.varna.models;

/**
 * Created by Lyubomir Babev on 8/26/2017.
 */

public class BusSchedule extends Object {

    private int     mNumber;
    private String  mSchedules;

    public BusSchedule(int number, String schedules) {
        this.mNumber        = number;
        this.mSchedules     = schedules;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getSchedules() {
        return mSchedules;
    }
}

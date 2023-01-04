package com.svu.bus.model;

import java.util.ArrayList;

public class Bus {
    private String id;
    private String pathId;
    private String driverId;
    private boolean state;
    private double lat;
    private double lan;
    private ArrayList<Reservition> reservitions;
    private int setNumber;

    public int getSetNumber() {
        return setNumber;
    }

    public Bus setSetNumber(int setNumber) {
        this.setNumber = setNumber;
        return this;
    }

    public ArrayList<Reservition> getReservitions() {
        return reservitions;
    }

    public Bus setReservitions(ArrayList<Reservition> reservitions) {
        this.reservitions = reservitions;
        return this;
    }

    public Bus() {
    }

    public String getId() {
        return id;
    }

    public Bus setId(String id) {
        this.id = id;
        return this;
    }

    public String getPathId() {
        return pathId;
    }

    public Bus setPathId(String pathId) {
        this.pathId = pathId;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public Bus setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public boolean isState() {
        return state;
    }

    public Bus setState(boolean state) {
        this.state = state;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Bus setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLan() {
        return lan;
    }

    public Bus setLan(double lan) {
        this.lan = lan;
        return this;
    }

}

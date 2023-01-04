package com.svu.bus.model;

import java.util.ArrayList;

public class Maintenance {
    private String busId;
    private String description;
    private String state;
    private String dateTime;

    public Maintenance() {
    }

    public String getBusId() {
        return busId;
    }

    public Maintenance setBusId(String busId) {
        this.busId = busId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Maintenance setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }

    public Maintenance setState(String state) {
        this.state = state;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Maintenance setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}

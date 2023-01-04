package com.svu.bus.model;

public class Reservition {
    private String userId;
    private String dateTime;
    private String payId;
    private String busId;
    private boolean state;

    public Reservition() {
    }

    public String getUserId() {
        return userId;
    }

    public Reservition setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Reservition setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getPayId() {
        return payId;
    }

    public Reservition setPayId(String payId) {
        this.payId = payId;
        return this;
    }

    public String getBusId() {
        return busId;
    }

    public Reservition setBusId(String busId) {
        this.busId = busId;
        return this;
    }

    public boolean isState() {
        return state;
    }

    public Reservition setState(boolean state) {
        this.state = state;
        return this;
    }
}

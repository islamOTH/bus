package com.svu.bus.model;

public class Pay {
    private String fromUseId;
    private String toUserId;
    private String description;
    private double amount;
    private String title;
    private String date;

    public Pay() {
    }

    public String getFromUseId() {
        return fromUseId;
    }

    public Pay setFromUseId(String fromUseId) {
        this.fromUseId = fromUseId;
        return this;
    }

    public String getToUserId() {
        return toUserId;
    }

    public Pay setToUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pay setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Pay setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Pay setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Pay setDate(String date) {
        this.date = date;
        return this;
    }
}

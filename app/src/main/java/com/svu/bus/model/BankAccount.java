package com.svu.bus.model;

import java.util.ArrayList;

public class BankAccount {
    private String userID;
    private double balance;
    private String password;
    private ArrayList<Pay> paysto;
    private ArrayList<Pay> paysfrom;

    public BankAccount() {
        paysfrom=new ArrayList<>();
        paysto=new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public BankAccount setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public BankAccount setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BankAccount setPassword(String password) {
        this.password = password;
        return this;
    }


    public ArrayList<Pay> getPaysto() {
        return paysto;
    }

    public BankAccount setPaysto(ArrayList<Pay> paysto) {
        this.paysto = paysto;
        return this;
    }

    public ArrayList<Pay> getPaysfrom() {
        return paysfrom;
    }

    public BankAccount setPaysfrom(ArrayList<Pay> paysfrom) {
        this.paysfrom = paysfrom;
        return this;
    }
}

package com.svu.bus.model;

import java.util.HashMap;
import java.util.Map;

public class BankAccount
{
    private String id;
    private double balance;
    private String userId;
    private String password;
    private String dateC;

    public BankAccount() {}

    public BankAccount(String id) {this.id = id;}

    public BankAccount(String id, double balance, String userId, String password, String dateC) {this.id = id;this.balance = balance;this.userId = userId;this.password = password;this.dateC = dateC;}

    public String getId() {return id;}

    public BankAccount setId(String id) {this.id = id;return this;}

    public double getBalance() {return balance;}

    public BankAccount setBalance(double balance) {this.balance = balance;return this;}

    public String getUserId() {
        return userId;
    }

    public BankAccount setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BankAccount setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDateC() {
        return dateC;
    }

    public BankAccount setDateC(String dateC) {
        this.dateC = dateC;
        return this;
    }

    public Map<String,Object > toMap()
    { Map<String ,Object> map=new HashMap<>();
        map.put( "balance",balance);
        map.put("userId",userId);
        map.put("password",password);
        map.put("dateC",dateC);
        return map;
    }
    public void fromMap(Map<String,Object> map)
    {
        this.balance=Double.parseDouble(map.get("balance")+"");
        this.dateC=map.get("dateC")+"";
        this.userId=map.get("userId")+"";
        this.password=map.get("password")+"";
    }


}

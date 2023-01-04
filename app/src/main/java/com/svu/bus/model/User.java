package com.svu.bus.model;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class User
{
    private String id;
    private String name;
    private String email;
    private String password;
    private String rule;
    private String phone;
    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRule() {
        return rule;
    }

    public User setRule(String rule) {
        this.rule = rule;
        return this;
    }

    public User(String id, String name, String email, String password, String rule, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rule = rule;
        this.phone = phone;
    }
   @Inject
    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Map<String , Object> toMap()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("email",email);
        map.put("password",password);
        map.put("phone",phone);
        map.put("rule",rule);
        return map;
    }
    public  User fromMap(Map<String,Object> map)
    {
    return   setEmail(map.get("email").toString())
            .setName(map.get("name").toString())
            .setPassword(map.get("password").toString())
            .setPhone(map.get("phone").toString())
            .setRule(map.get("rule").toString());
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rule='" + rule + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

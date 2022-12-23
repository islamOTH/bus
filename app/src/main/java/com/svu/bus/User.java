package com.svu.bus;

import java.util.HashMap;
import java.util.Map;

public class User
{
    private int id;
    private String name;
    private String email;
    private String password;
    private String rule;


    public int getId() {
        return id;
    }

    public User setId(int id) {
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

    public Map<String , Object> toMap()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("email",email);
        map.put("password",password);
        map.put("rule",rule);
        return map;
    }
    public static User fromMap(Map<String,Object> map)
    {
    return new User()
            .setEmail(map.get("email").toString())
            .setId(Integer.parseInt(map.get("id").toString()))
            .setName(map.get("name").toString())
            .setPassword(map.get("password").toString())
            .setRule(map.get("rule").toString());
    }

}

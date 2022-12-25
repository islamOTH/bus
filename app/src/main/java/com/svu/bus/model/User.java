package com.svu.bus.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.svu.bus.controller.InsertAction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@IgnoreExtraProperties

public class User implements Serializable
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
    @Exclude

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

    public void insert(InsertAction insertAction)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Add a new document with a generated ID
        db.collection("users")
                .add(this.toMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    id=documentReference.getId();
                    insertAction.insertComplate(User.this);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        insertAction.insertError(e.getMessage().toString());
                    }
                });
    }

}

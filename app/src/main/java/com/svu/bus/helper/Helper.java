package com.svu.bus.helper;

import android.app.ProgressDialog;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.svu.bus.model.User;

import java.util.ArrayList;

import dagger.Component;

@Component(modules = HelperModuel.class)
public interface Helper
{
    FirebaseDatabase getFirebaseDatabase();
    FirebaseFirestore getFirebaseFirestore();
    ArrayList<User> getUserArrayList();
    MyRefrence getMyRefrence();
    MyDialog getMyDialog();
    ProgressDialog getDialog();
    MyAnimation getMyAnimation();
    MyDB getMyDb();

}

package com.svu.bus.helper;


import android.content.Context;
import android.content.SharedPreferences;
import com.svu.bus.MyApp;
import javax.inject.Inject;

public class MyRefrence {
    private static final String TAG ="MyRefrence" ;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String dbName="sharedDb";
    @Inject
    public MyRefrence() {preferences= MyApp.getContext().getSharedPreferences(dbName,Context.MODE_PRIVATE);editor=preferences.edit();}
    public void insertId(String id) {editor.putString("id",id).apply();}
    public void clearId(){editor.putString("id",null).apply();}
    public String getId(){return preferences.getString("id",null);}

}

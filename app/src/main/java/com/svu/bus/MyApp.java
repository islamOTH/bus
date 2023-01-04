package com.svu.bus;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static MyApp instance;
    private static MainActivity activity;

    public static MainActivity getActivity() {
        return activity;
    }

    public static void setActivity(MainActivity activity) {
        MyApp.activity = activity;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
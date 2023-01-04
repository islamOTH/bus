package com.svu.bus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.database.FirebaseDatabase;
import com.svu.bus.databinding.ActivityMainBinding;
import com.svu.bus.helper.Helper;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.FragmentModule;
import com.svu.bus.view.fragments.Fragments;


import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public Fragments fragments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyApp.setActivity(this);
        fragments= DaggerFragments.create();

        if (ContextCompat.checkSelfPermission(MyApp.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MyApp.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(MyApp.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else
            {
                ActivityCompat.requestPermissions(MyApp.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainFrame,fragments.getSplashFragment())
                .commit();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            finish();
        }


    }
}

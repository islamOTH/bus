package com.svu.bus.view.viewmodel;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.GoogleMap;
import com.google.type.LatLng;
import com.svu.bus.MyApp;
import com.svu.bus.R;
import com.svu.bus.helper.DaggerHelper;
import com.svu.bus.helper.MyDB;
import com.svu.bus.helper.MyRefrence;
import com.svu.bus.model.User;
import com.svu.bus.view.fragments.DaggerFragments;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserViewModel extends ViewModel {
    public MutableLiveData<User> userliveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<User>> listAllUserLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<User>> listUserLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<User>> listDriverLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<User>> listControllerLiveData = new MutableLiveData<>();
    public MutableLiveData<Location> locationMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ArrayList<User>> listAllUserForLoginLiveData = new MutableLiveData<>();


    public MyRefrence myRefrence = DaggerHelper.create().getMyRefrence();
    public MyDB db = DaggerHelper.create().getMyDb();

    public void getAllUser() {
        db.getAllUser();
    }

    public void getAllUserForLogin() {
        db.getAllUserForLogin();
    }

    public void getOneUserFromRefrence() {
        if (myRefrence.getId() == null) {
            this.userliveData.setValue(null);
        } else {
            db.getOneUser(myRefrence.getId());
        }
    }

    public void insertUser(User user) {
        db.insertUser(user);
    }

    public void clear() {
        userliveData = new MutableLiveData<>();
        listAllUserLiveData = new MutableLiveData<>();
        listUserLiveData = new MutableLiveData<>();
        listDriverLiveData = new MutableLiveData<>();
        listControllerLiveData = new MutableLiveData<>();
        listAllUserForLoginLiveData = new MutableLiveData<>();
    }

    public void clearUser() {
        userliveData = new MutableLiveData<>();
    }

    public void deleteUser(User user) {
        db.deleteUser(user);
    }

    public void editeUser(User user) {
        userliveData.setValue(user);
        MyApp.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.mainFrame, DaggerFragments.create().getMyUserInfoFragment())
                .commit();
    }


    public void updateUser(User user) {
        db.updateUser(user);
    }


    public void advancedProfile(User user) {

    }


    public void onLocationChange() {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) MyApp.getActivity().getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location)
            {

                locationMutableLiveData.setValue(location);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(MyApp.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyApp.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }



}

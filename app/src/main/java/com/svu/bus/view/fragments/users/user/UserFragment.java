package com.svu.bus.view.fragments.users.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svu.bus.MyApp;
import com.svu.bus.R;
import com.svu.bus.view.viewmodel.UserViewModel;

import javax.inject.Inject;

public class UserFragment extends Fragment {
    private GoogleMap googleMap;
    private UserViewModel userViewModel;
    private Location myLocation=null;
    @Inject
    public UserFragment(UserViewModel userViewModel) {
        this.userViewModel=userViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.svu.bus.R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map))
                .getMapAsync(googleMap -> initMap(googleMap));

    }

    private void initMap(GoogleMap googleMap) {
        this.googleMap = googleMap;


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            userViewModel.onLocationChange();
            userViewModel
                    .locationMutableLiveData
                    .observe(getActivity(),location -> {
                        if (myLocation==null)
                        {googleMap.clear();
                            LatLng yourLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(yourLocation).title("your Location"));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(yourLocation,18));
                        }
                        else
                        {


                            googleMap.clear();
                            LatLng yourLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions()
                                    .position(yourLocation)
                                    .title("Title")
                                    .snippet("Snippet")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));
                        }
                        myLocation=location;});

    }



}
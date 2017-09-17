package com.sparkmap.sparkmap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.R.attr.fragment;


/**
 * Created by Nate on 9/14/2017.
 */

public class Location extends AppCompatActivity {
    Activity activity;
    SupportMapFragment supportMapFragment;
    android.support.v4.app.FragmentManager sFM;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION = 101;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    public Location(MainActivity passedactivity, GoogleMap googleMap) {
        this.activity = passedactivity;
        passedactivity.makeSureLocationOn();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<android.location.Location>() {
                    @Override
                    public void onSuccess(android.location.Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            float lat = (float) (location.getLatitude());
                            float lng = (float) (location.getLongitude());
                            LatLng CurrentLocation = new LatLng(lat, lng);
                            float zoomLevel = (float) 14.0;
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CurrentLocation, zoomLevel));
                        }
                    }
                });
    }


}




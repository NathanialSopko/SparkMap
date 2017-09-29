package com.sparkmap.sparkmap;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

import static android.R.attr.fragment;


/**
 * Created by Nate on 9/14/2017.
 */

public class Location extends AppCompatActivity {
    MainActivity activity;
    SupportMapFragment supportMapFragment;
    android.support.v4.app.FragmentManager sFM;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION = 101;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private HashMap<String, String> markerMap;
    private Map mapClass;

    public Location(MainActivity passedactivity, GoogleMap googleMap) {

        markerMap = new HashMap<String, String>();
        this.activity = passedactivity;
        makeSureLocationOn();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mMap = googleMap;
        mapClass = new Map(mMap, this, passedactivity);
        mapClass.centerCam();
        FAB myFab = new FAB(activity, this, mMap, mapClass);//Creates an instance of FAB that adds the floating action buttons to the main activity

    }

    public void makeSureLocationOn() {
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        //startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public FusedLocationProviderClient getFusedLocationClient(){
        return mFusedLocationClient;
    }

    public MainActivity getActivity() {return activity;}

    public HashMap<String, String> getMarkerMap(){
        return markerMap;
    }

}




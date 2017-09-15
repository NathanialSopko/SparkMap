package com.sparkmap.sparkmap;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by Nate on 9/14/2017.
 */

public class Location extends AppCompatActivity implements OnMapReadyCallback{
    Activity activity;
    SupportMapFragment supportMapFragment;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION = 101;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private String provider;
    private boolean locationON;
    private android.location.Location location;
    private FusedLocationProviderClient mFusedLocationClient;
    private int off = 0;
    private android.support.v4.app.FragmentManager sFM;

    public Location(Activity activity) {
        this.activity = activity;
        supportMapFragment = SupportMapFragment.newInstance();

    }
    public SupportMapFragment getMapFrag(){
        return supportMapFragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

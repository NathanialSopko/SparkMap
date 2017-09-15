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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        supportMapFragment.getMapAsync(this);
        sFM = getSupportFragmentManager();
        sFM.beginTransaction().replace(R.id.map, supportMapFragment).commit();
    }
    public SupportMapFragment getMapFrag(){
        return supportMapFragment;
    }
    public void makeSureLocationOn(){
        try {
            off = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if(off==0){
            AlertDialog alertDialog = new AlertDialog.Builder(Location.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Please turn on location services for SparkMap then press back on your phone, thank you.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent onGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(onGPS);
                        }
                    });
            alertDialog.show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }
}

package com.sparkmap.sparkmap;

import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


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
    private Map mapClass;

    public Location(MainActivity passedactivity, GoogleMap googleMap) {
        this.activity = passedactivity;
        makeSureLocationOn();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mMap = googleMap;
        mapClass = new Map(mMap, this, passedactivity);
        mapClass.centerCam();
        FAB myFab = new FAB(activity, this, mMap, mapClass);//Creates an instance of FAB that adds the floating action buttons to the main activity
    }

    /**
     * This is a simple check to make sure that the location services are allowed for our app
     *
     * If they are not there is a simple message sent to the user
     */
    public boolean checkLocationEnabled(){
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return false;
        }
        else{
            return true;
        }
    }
    public void makeSureLocationOn() {
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

       // if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        //    buildAlertMessageNoGps();
        //}
        if(!checkLocationEnabled()){
            buildAlertMessageNoGps();
        }
    }

    /**
     * This alert is sent to the user if location is not available
     */
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Please enable your location for SparkMap. Thank you!")
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

}




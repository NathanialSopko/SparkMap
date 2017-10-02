package com.sparkmap.sparkmap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment supportMapFragment;
    Location myLocation;
    GoogleMap mMap;
    android.support.v4.app.FragmentManager sFM;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //begin login activity
        Intent logInt = new Intent(this, LoginActivity.class);
        startActivity(logInt);
        /*
         * I need the next 4 lines here and the onMapReady function because when I move it to the location
         * function and just call new location there it breaks.
         */
        supportMapFragment = SupportMapFragment.newInstance();
        supportMapFragment.getMapAsync(this);
        sFM = getSupportFragmentManager();
        sFM.beginTransaction().replace(R.id.map, supportMapFragment).commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        myLocation = new Location(this, mMap);//Creates an instance of the Location class that adds the map/creates location functions to/for the main activity
    }
}

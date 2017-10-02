package com.sparkmap.sparkmap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        LatLng CurrentLocation = new LatLng((43.0013), (-78.7872));
        float zoomLevel = (float) 12.7;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CurrentLocation, zoomLevel));
        //--------------------------------------------------------------
        LatLng ll1 = new LatLng((43.000819),(-78.788991));
        mMap.addMarker(new MarkerOptions().position(ll1)
                .title("Paul DeGennero")
                .snippet("CSE 442 study session in Capen right now!"));
        //--------------------------------------------------------------
        LatLng ll2 = new LatLng((43.001400),(-78.785837));
        mMap.addMarker(new MarkerOptions().position(ll2)
                .title("Drew Wentka")
                .snippet("Free hotdogs outside of SU!"));
        //--------------------------------------------------------------
        LatLng ll3 = new LatLng((43.000654),(-78.782972));
        mMap.addMarker(new MarkerOptions().position(ll3)
                .title("Nate Sopko")
                .snippet("Free student concert at CFA"));
        //--------------------------------------------------------------
        LatLng ll4 = new LatLng((43.002781),(-78.784882));
        mMap.addMarker(new MarkerOptions().position(ll4)
                .title("George Urban")
                .snippet("Prices are too high here - will buy online instead!"));
        //--------------------------------------------------------------
        LatLng ll5 = new LatLng((42.997757),(-78.779192));
        mMap.addMarker(new MarkerOptions().position(ll5)
                .title("Nick Smith")
                .snippet("Pre-game concert is super sick"));
        //--------------------------------------------------------------
        LatLng ll6 = new LatLng((43.002781),(-78.784882));
        mMap.addMarker(new MarkerOptions().position(ll6)
                .title("George Urban")
                .snippet("Prices are too high here - will buy online instead!"));

        myLocation = new Location(this, mMap);//Creates an instance of the Location class that adds the map/creates location functions to/for the main activity
    }
}

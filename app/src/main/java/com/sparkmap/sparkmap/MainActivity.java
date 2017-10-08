package com.sparkmap.sparkmap;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    static int MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION = 101;
    SupportMapFragment supportMapFragment;
    Location myLocation;
    GoogleMap mMap;
    android.support.v4.app.FragmentManager sFM;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Retrieve an instance of your database using getInstance()
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //You can save a range of data types to the database this way, including Java objects.
        DatabaseReference myRef = database.getReference("message");
        // Write a message to the database
        //myRef.setValue("Hello, World!");

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

    public mainTools getMainTools(){
        return new mainTools(sFM, supportMapFragment, getApplicationContext());
    }

    public void doEmail(Boolean isShare) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"pauldege@buffalo.edu"});
        if(isShare) {
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out SparkMap!");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Go check out this brand new social media called SparkMap! Rather than a lame linear timeline, " +
                    "you can post and views post to a map and see whats going on around you!");
            emailIntent.setType("text/plain");
        }else{
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"sparkmap.team@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Fill appropriate subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear SparkMap team, \n");
            emailIntent.setType("text/plain");
        }
        try {
            startActivity(emailIntent);


        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"There was an error.", Toast.LENGTH_SHORT).show();
        }

    }

}

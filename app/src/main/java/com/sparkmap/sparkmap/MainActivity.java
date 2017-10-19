package com.sparkmap.sparkmap;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment supportMapFragment;
    Location myLocation;
    GoogleMap mMap;
    android.support.v4.app.FragmentManager sFM;

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
//        Intent logInt = new Intent(this, LoginActivity.class);
//        startActivity(logInt);




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

    //Creates mapTools class. Used in navdrawer for various things, may be less neccessary now.
    public mainTools getMainTools(){
        return new mainTools(sFM, supportMapFragment, getApplicationContext());
    }

    /*******************************************
     *Fragment methods, all called from navDrawer. Have to be in here since this activity is their parent
     *******************************************
     */

    //loads profile fragment
    public void doProfile(NavDrawer navDrawer){
        if (findViewById(R.id.prof_fragment_container) != null) {
            ProfileFragment profileFragment = new ProfileFragment();
            //hide the map frag
             mapFragShow(false);
            //replace view with profile fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.prof_fragment_container, profileFragment).addToBackStack(null).commit();
        }
    }

    //hides or shows the mapFrag based on arguement
    public void mapFragShow(Boolean show) {
        if(!show) {
            sFM.beginTransaction().hide(supportMapFragment).commit();
        }else{
            sFM.beginTransaction().show(supportMapFragment).commit();
        }

    }

    //For sharing, will either automate an email to us, our to a friend depending on input. This is also called from NavDrawer
    public void doEmail(Boolean isShare) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        if(isShare) {
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out SparkMap!");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Go check out this brand new social media called SparkMap! Rather than a lame linear timeline, " +
                    "you can Spark and view other Sparks on a map to see whats going on around you!\n Click the below url to download the app!\n goo.gl/dDZMra \n\n -Your friend, \n");
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

    /*This will show the mapFrag and get rid of another fragment on top of the mapFrag.
     *If the mapFrag is already showing, thats fine, it the back key will act as normal.
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            mapFragShow(true);
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }

    }

}

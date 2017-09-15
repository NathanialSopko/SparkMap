package com.sparkmap.sparkmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Location myLocation = new Location(this);
        FAB myFab = new FAB(this, myLocation); //Creates an instance of FAB that adds the floating action buttons to the main activity
        NavDrawer myNavDrawer = new NavDrawer(this, myLocation); //Creates an instance of NavDrawer that adds the navigation drawer to the main activity
         //Creates an instance of the Location class that adds the map/creates location functions to/for the main activity
    }

}

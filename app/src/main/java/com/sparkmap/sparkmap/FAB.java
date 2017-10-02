package com.sparkmap.sparkmap;

import android.app.Activity;
import android.view.View;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.GoogleMap;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by Nate on 9/14/2017.
 */
//This is a comment
public class FAB extends FragmentActivity {
    Activity activity;
    Location myLocation;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3; //all of the FAB's for referencing

    public FAB(final MainActivity activity, Location passedLocation, GoogleMap googleMap, final Map mapClass){
        this.activity=activity;
        myLocation = passedLocation;
        //need to assign these buttons to the actual xml id's that are in app_bar_main.xml
        materialDesignFAM = (FloatingActionMenu) activity.findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) activity.findViewById(R.id.floating_spark);
        floatingActionButton2 = (FloatingActionButton) activity.findViewById(R.id.floating_refresh);
        floatingActionButton3 = (FloatingActionButton) activity.findViewById(R.id.floating_compass);


        //setting all of the onclicklisteners for each of the buttons (what they do)
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapClass.createSpark();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapClass.refreshSparks();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapClass.centerCam();
            }
        });
        NavDrawer myNavDrawer = new NavDrawer(activity, myLocation, this);//Creates an instance of NavDrawer that adds the navigation drawer to the main activity
    }

}

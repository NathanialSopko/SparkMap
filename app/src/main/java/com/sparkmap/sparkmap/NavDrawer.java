package com.sparkmap.sparkmap;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by Nate on 9/14/2017.
 */

public class NavDrawer  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Activity activity;
    SupportMapFragment _supportMapFragment;
    private Location myLocation;
    private FAB myFab;


    public NavDrawer(Activity activity, Location passedLocation, FAB passedFab, String userData, SupportMapFragment supportMapFragment){
        myLocation = passedLocation;
        _supportMapFragment = supportMapFragment;
        myFab = passedFab;
        this.activity=activity;
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        ((AppCompatActivity) activity).setSupportActionBar( toolbar);
        //setSupportActionBar(toolbar); //this was the original call from the onCreate method but I switched it to use this activity passed in

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        try {
            loadUserData(navigationView, userData);
        } catch (JSONException e) {
            Log.d("logIn", "didnt work");
            e.printStackTrace();
        }
    }
    public void loadUserData(NavigationView navigationView, String userData) throws JSONException {

        JSONObject reader = new JSONObject(userData);
        String userName = (String) reader.get("userName");
        String userEmail = (String) reader.get("userEmail");
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.userName);
        TextView nav_userEmail = (TextView)hView.findViewById(R.id.userEmail);
        nav_user.setText(userName);
        nav_userEmail.setText(userEmail);
        Log.d("logIn", userName);

    }

    //https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android/13814551#13814551


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_map) {


        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }
}

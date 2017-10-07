package com.sparkmap.sparkmap;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Nate on 9/20/2017.
 */

public class Map extends AppCompatActivity implements OnInfoWindowClickListener {
    private GoogleMap mMap;
    private Location mLocation;
    private HashMap<String, String> mMarkerMap;
    private MainActivity mainActivity;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION = 101;
    private String m_Text;
    float lat;
    float lng;
    private boolean firstRun = true;
    private FirebaseDatabase database;
    public Map(GoogleMap passedMap, Location passedLocation, MainActivity passedActivity){
        mMap = passedMap;
        mLocation = passedLocation;
        mMarkerMap = mLocation.getMarkerMap();
        mMap.setOnInfoWindowClickListener(this);
        mainActivity = passedActivity;
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(mainActivity, "This message", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("View Spark");
        final TextView Title = new TextView(mainActivity);
        Title.setText(marker.getTitle());
        final TextView snippet = new TextView(mainActivity);
        snippet.setText(marker.getSnippet());
        LinearLayout ll = new LinearLayout(mainActivity);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(Title);
        ll.addView(snippet);
        builder.setView(ll);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    public void writeSparkToDatabase(){
        //TODO: make this method write the new spark to database when it is made
    }

    /**
     *This method takes in data from the database and parses it out into values for the google map markers
     *
     */
    public void parseSpark(Spark input){
        addMapMarker(Float.parseFloat(input.getLat()),Float.parseFloat(input.getLng()),input.getTitle(),input.getSnippet(), false);
    }

    /**
     * Refresh Sparks is used to pull all of the sparks from the database into the current map and refresh
     * all of the others on the screen currently
     */

    public void refreshSparks(){
        mMap.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Sparks");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Spark input = snapshot.getValue(Spark.class);
                            parseSpark(input);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(mainActivity,"Error loading Sparks", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     *
     * @param lat this is a latitude reading that will be passed in
     * @param lng this is a longitude reading that will be passed in
     * @param title this is the title of the spark
     * @param snippet this is the description of the spark
     * @param dbBool this is true if you want it to be added to the database when it is created and false by default for loading sparks
     *
     *                 This method should simply take in a latitude and longitude and add a marker to the map in that spot.
     *                 Then it should save that marker in the markerMap hashmap so that we know it was added (username, (lat,lng)).
     *                 It will then be added to the database under the Sparks container
     */
    public void addMapMarker(float lat, float lng, String title, String snippet, boolean dbBool){
        LatLng currentLocation = new LatLng(lat, lng);
        if(currentLocation != null && title.length() !=0 && snippet.length()!=0){
            mMap.addMarker(new MarkerOptions().position(currentLocation)
                    .title(title)
                    .snippet(snippet));
            if(dbBool == true) {
                DatabaseReference myRef = database.getReference("Sparks");
                String key = myRef.push().getKey();
                Spark insert = new Spark();
                insert.setpLat(Float.toString(lat));
                insert.setpLng(Float.toString(lng));
                insert.setpSnippet(snippet);
                insert.setpTitle(title);
                myRef.child(key).setValue(insert);
            }
        }
        else {
            if (currentLocation == null) {
                Toast.makeText(mainActivity, "Location cannot be determined.", Toast.LENGTH_SHORT).show();
            } else if (title.length() == 0) {
                Toast.makeText(mainActivity, "Title cannot be empty.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mainActivity, "Description cannot be empty.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void centerCam(){
        FusedLocationProviderClient mFusedLocationClient = mLocation.getFusedLocationClient();
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION);
            }
            return;
        }
        if(firstRun == true) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            refreshSparks();
            firstRun = false;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(mainActivity, new OnSuccessListener<android.location.Location>() {
                    @Override
                    public void onSuccess(android.location.Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            float lat = (float) (location.getLatitude());
                            float lng = (float) (location.getLongitude());
                            LatLng CurrentLocation = new LatLng(lat, lng);
                            float zoomLevel = (float) 14.0;
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CurrentLocation, zoomLevel));
                        }
                    }
                });
    }

    public void createSpark() {
        Toast.makeText(mainActivity, "Create Spark", Toast.LENGTH_SHORT).show();
        FusedLocationProviderClient mFusedLocationClient = mLocation.getFusedLocationClient();
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS__FINE_LOCATION);
            }
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(mainActivity, new OnSuccessListener<android.location.Location>() {
                    @Override
                    public void onSuccess(android.location.Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            lat = (float) (location.getLatitude());
                            lng = (float) (location.getLongitude());
                            //Alert for spark creation input
                            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                            builder.setTitle("Create a Spark!");
                            // Set up the input
                            final EditText Title = new EditText(mainActivity);
                            Title.setHint("Title of Spark");
                            final EditText snippet = new EditText(mainActivity);
                            snippet.setHint("Describe the Spark");
                            Title.setInputType(InputType.TYPE_CLASS_TEXT);
                            snippet.setInputType(InputType.TYPE_CLASS_TEXT);
                            LinearLayout ll = new LinearLayout(mainActivity);
                            ll.setOrientation(LinearLayout.VERTICAL);
                            ll.addView(Title);
                            ll.addView(snippet);
                            builder.setView(ll);
                            builder.setPositiveButton("Spark!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String m_title = Title.getText().toString();
                                    String m_snippet = snippet.getText().toString();
                                    //get the username associated to the user making the post
                                    //FirebaseUser user = firebaseAuth.getCurrentUser();
                                    //String username = databaseReference.child(user.getUid().getValue(userName);
                                    SimpleDateFormat df = new SimpleDateFormat("MMM dd, HH:mm:ss");
                                    Date currentTime = Calendar.getInstance().getTime();
                                    String date = df.format(currentTime);
                                    m_snippet += (" - "+date);
                                    addMapMarker(lat, lng, m_title, m_snippet, true);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }
                    }
                });
    }
}

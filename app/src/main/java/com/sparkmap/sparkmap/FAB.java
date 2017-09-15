package com.sparkmap.sparkmap;

import android.app.Activity;
import android.view.View;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import android.support.v4.app.FragmentActivity;
/**
 * Created by Nate on 9/14/2017.
 */
//This is a comment
public class FAB extends FragmentActivity {
    Activity activity;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5, floatingActionButton6; //all of the FAB's for referencing

    public FAB(Activity activity){
        this.activity=activity;
        
        //need to assign these buttons to the actual xml id's that are in app_bar_main.xml
        materialDesignFAM = (FloatingActionMenu) activity.findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) activity.findViewById(R.id.floating_facebook);
        floatingActionButton2 = (FloatingActionButton) activity.findViewById(R.id.floating_twitter);
        floatingActionButton3 = (FloatingActionButton) activity.findViewById(R.id.floating_linkdin);
        floatingActionButton4 = (FloatingActionButton) activity.findViewById(R.id.floating_google_plus);
        floatingActionButton5 = (FloatingActionButton) activity.findViewById(R.id.floating_instagram);
        floatingActionButton6 = (FloatingActionButton) activity.findViewById(R.id.floating_youtube);


        //setting all of the onclicklisteners for each of the buttons (what they do)
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                System.exit(0);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                System.exit(0);
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                System.exit(0);
            }
        });

        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                System.exit(0);
            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                System.exit(0);
            }
        });
        floatingActionButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                System.exit(0);
            }
        });
    }

}

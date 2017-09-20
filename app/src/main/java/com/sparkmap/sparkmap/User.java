package com.sparkmap.sparkmap;

import android.util.Log;

/**
 * Created by pauldegennaro on 9/13/17.
 */

public class User {

    //user passed is full email
    //TODO load userinfo from firebase, maybe just store it all as .json
    public User(String user){

        Log.d("logIn", user);
    }

}

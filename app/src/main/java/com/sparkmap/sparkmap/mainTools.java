package com.sparkmap.sparkmap;

import android.content.Context;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by pauldegennaro on 10/5/17.
 */

public class mainTools {
    android.support.v4.app.FragmentManager _sFM;
    SupportMapFragment _supportMapFragment;
    Context _Context;

    mainTools(android.support.v4.app.FragmentManager sFM, SupportMapFragment supportMapFragment, Context currContext){
        _sFM = sFM;
        _supportMapFragment = supportMapFragment;
        _Context = currContext;

    }

    public android.support.v4.app.FragmentManager getFragMan(){
        return _sFM;
    }

    public SupportMapFragment getFragSupp(){
        return _supportMapFragment;
    }

    public Context getContext(){
        return _Context;
    }

}

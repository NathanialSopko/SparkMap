package com.sparkmap.sparkmap;

/**
 * Created by Nate on 10/6/2017.
 */

public class Spark {
    String lat;
    String lng;
    String title;
    String snippet;

    public void setpLat(String pLat) {
        this.lat = pLat;
    }

    public void setpLng(String pLng) {
        this.lng = pLng;
    }

    public void setpTitle(String pTitle) {
        this.title = pTitle;
    }

    public void setpSnippet(String pSnippet) {
        this.snippet = pSnippet;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }
}

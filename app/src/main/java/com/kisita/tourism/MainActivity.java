package com.kisita.tourism;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TabHost;

import com.kisita.tourism.util.ImageAdapter;

public class MainActivity extends AppCompatActivity {
   // private Intent form_hotels;
    //private Intent form_restaurants;
    //private Intent form_events;
    //private Intent history;
    private String province;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        //ActionBar mActionBar = getActionBar();
        //mActionBar.hide();

        //this.initializeActivities(this.getIntent().getIntExtra("ACTIVITY", 0));
        //this.initializeTabs();
        this.setTitle(getActivityTitle(this.getIntent().getIntExtra("ACTIVITY", 0)));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(this.getIntent().getIntExtra("ACTIVITY", 0)));
        //getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(new Bitmap(R.drawable.h1)));

        setContentView(R.layout.activity_grid);
        GridView gridView = (GridView)findViewById(R.id.GridView1);
        gridView.setAdapter(new ImageAdapter(this));


    }

    private String getActivityTitle(int activity) {
        switch (activity) {
            case CongoProvinces.kinshasa:
                province = "Kinshasa";
                break;
            case CongoProvinces.tshopo:
                province = "Tshopo";
                break;
            case CongoProvinces.sud_ubangi:
                province = "Sud-ubangi";
                break;
            case CongoProvinces.nord_ubangi:
                province = "Nord-ubangi";
                break;
            case CongoProvinces.mongala:
                province = "Mongala";
                break;
            case CongoProvinces.bas_uele:
                province = "Bas-uele";
                break;
            case CongoProvinces.haut_uele:
                province = "Haut-uele";
                break;
            case CongoProvinces.ituri:
                province = "Ituri";
                break;
            case CongoProvinces.nord_kivu:
                province = "Nord-kivu";
                break;
            case CongoProvinces.sud_kivu:
                province = "Sud-kivu";
                break;
            case CongoProvinces.maniema:
                province = "Maniema";
                break;
            case CongoProvinces.sankuru:
                province = "Sankuru";
                break;
            case CongoProvinces.mai_ndombe:
                province = "Maï-ndombe";
                break;
            case CongoProvinces.tshuapa:
                province = "Tshuapa";
                break;
            case CongoProvinces.kasai:
                province = "Kasai";
                break;
            case CongoProvinces.kwilu:
                province = "Kwilu";
                break;
            case CongoProvinces.kasai_central:
                province = "Kasai central";
                break;
            case CongoProvinces.kasai_oriental:
                province = "Kasai oriental";
                break;
            case CongoProvinces.lomami:
                province = "Lomami";
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Lomami_(province)");
                break;
            case CongoProvinces.haut_lomami:
                province = "Haut-lomami";
                // webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Lomami");
                break;
            case CongoProvinces.tanganyka:
                province = "Tanganyka";
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Tanganyika_(province)");
                break;
            case CongoProvinces.haut_katanga:
                province = "Haut-katanga";
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Katanga");
                break;
            case CongoProvinces.lualaba:
                province = "Lualaba";
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Lualaba_(province)");
                break;
            case CongoProvinces.kwango:
                province = "Kwango";
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Kwango_(province)");
                break;
            case CongoProvinces.kongo_central:
                province = "Kongo central";
                break;
            case CongoProvinces.equateur:
                province = "Equateur";
                break;
            default:
                System.out.println("color not recognized !!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
        }
        return province;
    }

    /*private void initializeActivities(int province)
    {
        // region Activities
        form_hotels = new Intent(this, FormActivity.class);
        form_hotels.putExtra("ACTIVITY", province);
        form_hotels.putExtra("TYPE","lodging");

        form_restaurants = new Intent(this, FormActivity.class);
        form_restaurants.putExtra("ACTIVITY", province);
        form_restaurants.putExtra("TYPE","restaurant");

        form_events = new Intent(this, FormActivity.class);
        form_events.putExtra("ACTIVITY", province);
        form_events.putExtra("TYPE","events");

        history = new Intent(this, HistoryActivity.class);
        history.putExtra("ACTIVITY",province);
    }

    private void initializeTabs()
    {
        TabHost.TabSpec spec = getTabHost().newTabSpec("tag0");
        spec.setContent(history);
        spec.setIndicator("", getResources().getDrawable(R.mipmap.historic));
        getTabHost().addTab(spec);


        spec = getTabHost().newTabSpec("tag1");
        spec.setContent(form_hotels);
        spec.setIndicator("", getResources().getDrawable(R.mipmap.hotels));
        getTabHost().addTab(spec);

        /////

        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(form_restaurants);
        spec.setIndicator("", getResources().getDrawable(R.mipmap.restaurant_red_round));
        getTabHost().addTab(spec);

        /////

        spec = getTabHost().newTabSpec("tag3");
        spec.setContent(form_events);
        spec.setIndicator("",getResources().getDrawable(R.mipmap.event));
        getTabHost().addTab(spec);
    }*/
}

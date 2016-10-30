package com.kisita.tourism;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kisita.tourism.util.ImageAdapter;
import com.kisita.tourism.util.ServicesDialog;

public class MainActivity extends AppCompatActivity {
   // private Intent form_hotels;
    //private Intent form_restaurants;
    //private Intent form_events;
    //private Intent history;
    private String province;
    private int mprovince;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        //ActionBar mActionBar = getActionBar();
        //mActionBar.hide();

        //this.initializeActivities(this.getIntent().getIntExtra("ACTIVITY", 0));
        //this.initializeTabs();
        mprovince = this.getIntent().getIntExtra("ACTIVITY", 0);
        this.setTitle(getActivityTitle(mprovince));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(mprovince));
        //getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(new Bitmap(R.drawable.h1)));

        setContentView(R.layout.activity_grid);
        GridView gridView = (GridView)findViewById(R.id.GridView1);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("GridView","item selected = "+position);
                Bundle args = new Bundle();
                args.putInt("province", mprovince);
                ServicesDialog dialog  = new ServicesDialog();
                switch(position)
                {
                    case 0://Restoration
                        args.putInt("service",ServicesDialog.restoration);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"restoration");
                        break;
                    case 1://Lodging
                        args.putInt("service",ServicesDialog.lodging);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"lodging");
                        break;
                    case 2:// Events
                        args.putInt("service",ServicesDialog.events);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"events");
                        break;
                    case 3://Culture
                        args.putInt("service",ServicesDialog.culture);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"culture");
                        break;
                    case 4://Health
                        args.putInt("service",ServicesDialog.health);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"health");
                        break;
                    case 5://spare-time activities
                        args.putInt("service",ServicesDialog.stActivities);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"spare-time");
                        break;
                    case 6://spare-time activities
                        args.putInt("service",ServicesDialog.beauty);
                        dialog.setArguments(args);
                        dialog.show(getSupportFragmentManager(),"beauty");
                        break;
                    default:
                        break;
                }
            }
        });


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

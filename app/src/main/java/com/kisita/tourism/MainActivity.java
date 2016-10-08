package com.kisita.tourism;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    private Intent form_hotels;
    private Intent form_restaurants;
    private Intent form_events;
    private Intent history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar mActionBar = getActionBar();
        //mActionBar.hide();

        this.initializeActivities(this.getIntent().getIntExtra("ACTIVITY", 0));
        this.initializeTabs();

    }

    private void initializeActivities(int province)
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
    }
}

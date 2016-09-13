package com.kisita.tourism;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    private Intent hotels;
    private Intent restoration;
    private Intent event;
    private Intent history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeActivities(this.getIntent().getIntExtra("ACTIVITY", 0));
        this.initializeTabs();
    }

    private void initializeActivities(int province)
    {
        // region Activities
        hotels = new Intent(this, HotelsActivity.class);
        hotels.putExtra("ACTIVITY",province);

        restoration = new Intent(this, RestorationActivity.class);
        restoration.putExtra("ACTIVITY",province);

        history = new Intent(this, HistoryActivity.class);
        history.putExtra("ACTIVITY",province);

        event = new Intent(this, EventActivity.class);
        event.putExtra("ACTIVITY",province);
    }

    private void initializeTabs()
    {
        TabHost.TabSpec spec = getTabHost().newTabSpec("tag0");
        spec.setContent(history);
        spec.setIndicator("", getResources().getDrawable(R.mipmap.historic));
        getTabHost().addTab(spec);


        spec = getTabHost().newTabSpec("tag1");
        spec.setContent(hotels);
        spec.setIndicator("", getResources().getDrawable(R.mipmap.hotels));
        getTabHost().addTab(spec);

        /////

        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(hotels);
        spec.setIndicator("", getResources().getDrawable(R.mipmap.restaurant_red_round));
        getTabHost().addTab(spec);

        /////

        spec = getTabHost().newTabSpec("tag3");
        spec.setContent(hotels);
        spec.setIndicator("",getResources().getDrawable(R.mipmap.event));
        getTabHost().addTab(spec);
    }
}

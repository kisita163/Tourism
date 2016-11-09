package com.kisita.tourism;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.kisita.tourism.util.GPSTracker;
import com.kisita.tourism.util.Place;
import com.kisita.tourism.util.PlacesFinder;
import com.kisita.tourism.util.PlacesFinderListener;
import com.kisita.tourism.util.ProvinceAdapter;
import com.kisita.tourism.util.ResultAdapter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ResultListActivity extends AppCompatActivity implements PlacesFinderListener {
    private double cLatitude;
    private double cLongitude;
    private ProgressDialog progressDialog;
    private String type = "lodging";
    private List<Place> places;
    private ListView list;
    private ResultAdapter adapter;
    private int province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        list = (ListView) findViewById(R.id.resultView);

        cLatitude = this.getIntent().getDoubleExtra("latitude", 0);
        cLongitude = this.getIntent().getDoubleExtra("longitude", 0);
        type = this.getIntent().getStringExtra("type");

        places = new ArrayList<>();

        province = this.getIntent().getIntExtra("province", 0);
        adapter = new ResultAdapter(this, places);

        list.setAdapter(adapter);

        getPoints();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.tshuapa)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result_list, menu);
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

        if (id == R.id.resultMap) {
            Intent intent = new Intent(this, GoogleMapsActivity.class);
            intent.putExtra("latitude", cLatitude);
            intent.putExtra("longitude", cLongitude);

            intent.putExtra("type", type);
            intent.putExtra("province", province);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void getPoints() {
        try {
            new PlacesFinder(cLatitude, cLongitude, type, this).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding places..!", true);

    }

    @Override
    public void onDirectionFinderSuccess(List<Place> place) {
        if (place == null) {
            return;
        }
        Location destination = new Location("destination");
        Location currPosition = new Location("currPosition");

        //currPosition.setLatitude(currPosition.getLatitude());

        adapter.notifyDataSetChanged();
        for (Place p : place) {

            destination.setLatitude(p.latitude);
            destination.setLongitude(p.longitude);
            currPosition.setLatitude(getIntent().getDoubleExtra("currLatitude", 0));
            currPosition.setLongitude(getIntent().getDoubleExtra("currLongitude", 0));

            p.distance = Math.round(destination.distanceTo(currPosition)/1000);
            adapter.add(p);

            Log.i(getClass().getName(), "distance = " + Math.round(destination.distanceTo(currPosition) / 1000));
        }

        adapter.notifyDataSetChanged();
    }
}

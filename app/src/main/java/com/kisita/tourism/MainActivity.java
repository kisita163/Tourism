package com.kisita.tourism;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kisita.tourism.util.ImageAdapter;
import com.kisita.tourism.util.ProvinceAdapter;
import com.kisita.tourism.util.ServicesDialog;
import com.kisita.tourism.util.XmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String province;
    private int mprovince;
    private XmlParser xmlParser;
    private XmlParser.City city;
    private XmlParser.City town;
    private InputStream stream = null;
    private double currLatitude;
    private double currLongitude;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mprovince = this.getIntent().getIntExtra("province", 0);

        currLatitude = this.getIntent().getDoubleExtra("latitude", 0);
        currLongitude = this.getIntent().getDoubleExtra("longitude", 0);

        this.setTitle(getActivityTitle(mprovince));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.tshuapa)));

        setContentView(R.layout.activity_grid);

        GridView gridView = (GridView)findViewById(R.id.GridView1);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("GridView", "item selected = " + position);
                Bundle args = new Bundle();
                args.putInt("province", mprovince);
                Log.i(getClass().getName(), "current position = " + currLatitude);

                args.putDouble("latitude", getProvinceLatitude(mprovince));
                args.putDouble("longitude",getProvinceLongitude(mprovince));
                args.putDouble("currLatitude",currLatitude);
                args.putDouble("currLongitude",currLongitude);

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
                    case 7://Transport
                        Intent transport = new Intent(MainActivity.this,TransportActivity.class);
                        startActivity(transport);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private String getActivityTitle(int activity) {

        return ProvinceAdapter.pNames[activity];
    }

    private double getProvinceLatitude(int province){
        //for(int s=0;s < CongoProvinces.provinces.length;s++){
        //    Log.i(getClass().getName(),c + " " + CongoProvinces.provinces[s][1] + " " + CongoProvinces.provinces[s][2]);
        //}

        return Double.valueOf(CongoProvinces.provinces[province][1]);
    }

    private double getProvinceLongitude(int province){
        return Double.valueOf(CongoProvinces.provinces[province][2]);
    }
}

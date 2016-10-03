package com.kisita.tourism;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kisita.tourism.util.Place;
import com.kisita.tourism.util.PlacesFinder;
import com.kisita.tourism.util.PlacesFinderListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback, PlacesFinderListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double cLatitude;
    private double cLongitude;
    private String type = "lodging";
    private List<Marker> placeMarkers = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps2);
        //setUpMapIfNeeded();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cLatitude = this.getIntent().getDoubleExtra("latitude", 0);
        cLongitude = this.getIntent().getDoubleExtra("longitude", 0);
        //type = this.getIntent().getStringExtra("type");

        getPoints();
    }


    private void getPoints(){
        try {
            new PlacesFinder(cLatitude,cLongitude,type,this).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng centre = new LatLng(cLatitude, cLongitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centre, 13));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding places..!", true);

        if (placeMarkers != null) {
            for (Marker marker : placeMarkers) {
                marker.remove();
            }
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Place> place) {

        if(place == null){
            return;
        }
        for(Place p:place){
            placeMarkers.add(mMap.addMarker(new MarkerOptions()
                    .title(p.name)
                    .position(new LatLng(p.latitude,p.longitude))));
        }
    }
}

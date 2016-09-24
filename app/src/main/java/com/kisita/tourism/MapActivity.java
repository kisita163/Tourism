package com.kisita.tourism;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MapActivity extends Activity {
    private MapView mMapView;
    private ItemizedIconOverlay<OverlayItem> markerOverlays;
    private MapController mMapController;
    private OverlayItem start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);

        //region get extra

        //endregion
        setMapController(this.getIntent().getDoubleExtra("latitude",-4349114),
                         this.getIntent().getDoubleExtra("longitude",15292118));

    }

    private void setMapController(double latitude,double longitude)
    {
        Log.i("map","latitude = "+latitude);
        Log.i("map","longitude = "+longitude);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(14);
        GeoPoint gPt = new GeoPoint(latitude,longitude);
        mMapController.setCenter(gPt);
    }
}

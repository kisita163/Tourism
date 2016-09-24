package com.kisita.tourism;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.kisita.tourism.util.DBAdapter;
import com.kisita.tourism.util.DBEntry;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends Activity {
    private MapView mMapView;
    private ItemizedIconOverlay<OverlayItem> markerOverlay;
    private MapController mMapController;
    private DBAdapter dbAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        dbAdapter.insertData(new DBEntry("Continental",-4.304346,15.312195,"hotel 3 etoiles","kinshasa"),"hotels");

        setMapController(this.getIntent().getDoubleExtra("latitude", -4349114),
                this.getIntent().getDoubleExtra("longitude", 15292118));

        //region set itimized overlay
        setItimizedOverlay();
        //endregion

    }

    private void setItimizedOverlay() {

        List<String> test = new ArrayList<>();
        test = dbAdapter.selectAllStudents();
        // To take from the data base
        final ArrayList<OverlayItem> items = new ArrayList<>();
        items.add(new OverlayItem("Gombe", "SampleDescription", new GeoPoint(-4.309535 ,15.292631)));
        items.add(new OverlayItem("Ngaliema", "SampleDescription", new GeoPoint(-4.372471, 15.254589)));
        items.add(new OverlayItem("Kintambo", "SampleDescription", new GeoPoint(-4.343598,15.267756)));



        			/* OnTapListener for the Markers, shows a simple Toast. */
        this.markerOverlay = new ItemizedIconOverlay<>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        Toast.makeText(
                                MapActivity.this,
                                item.getTitle() , Toast.LENGTH_SHORT).show();
                        return true; // We 'handled' this event.
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Toast.makeText(
                                MapActivity.this,
                                item.getTitle() , Toast.LENGTH_LONG).show();
                        return false;
                    }
                }, getApplicationContext());
        this.mMapView.getOverlays().add(this.markerOverlay);
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

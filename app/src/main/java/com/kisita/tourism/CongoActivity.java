package com.kisita.tourism;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Field;

public class CongoActivity extends Activity {
    private ImageView imageView;
    private Field[] fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congo);

        imageView = (ImageView)findViewById(R.id.visibleImage);

        fields = CongoProvinces.class.getDeclaredFields();


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();

                final int evX = (int) event.getX();
                final int evY = (int) event.getY();


                int k = 0;
                int prov = getHotspotColor(R.id.nonvisibleImage, evX, evY);

                for(Field f:fields)
                {
                    try {
                        k = f.getInt(null);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!! k = "+k);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    if(k == prov) {
                        Intent intent = new Intent(CongoActivity.this, MainActivity.class);
                        intent.putExtra("ACTIVITY",prov);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });

    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }
}

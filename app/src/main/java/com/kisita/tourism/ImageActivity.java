package com.kisita.tourism;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kisita.tourism.util.CustomSwipeAdapter;

public class ImageActivity extends Activity {
    private ViewPager viewPager;
    private CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");

        super.onResume();
    }

    @Override
    protected void onStart() {
        System.out.println("onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        System.out.println("onRestart");
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
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

        return super.onOptionsItemSelected(item);
    }
}

package com.kisita.tourism;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class StartActivity extends Activity {

    @Override
    protected void onRestart() {
        super.onRestart();
        this.finish();
        //System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(StartActivity.this,CongoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        thread.start();
    }

}

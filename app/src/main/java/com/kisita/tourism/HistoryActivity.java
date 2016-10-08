package com.kisita.tourism;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.io.IOException;


public class HistoryActivity extends AppCompatActivity {
    private WebView webview ;
    private TextView textView  ;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            //if Back key pressed and webview can navigate to previous page
            webview.goBack();
            // go back to previous page
            return true;
        }
        else
        {
            finish();
            // finish the activity
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        webview = (WebView) findViewById(R.id.webView);
        //textView = (TextView)findViewById(R.id.province_history);

        initializeUrl();
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
    }
    // region URL initializer
    private void initializeUrl() {
        /*try {
            String[] m = getAssets().getLocales();

            for(String s:m)
            {
                System.out.println("#############################" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        switch (this.getIntent().getIntExtra("ACTIVITY", -1))
        {
            case CongoProvinces.kinshasa:
                webview.loadUrl("file:///android_asset/kinshasa.html");
                break;
            case CongoProvinces.tshopo:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Tshopo_(province)");
                break;
            case CongoProvinces.sud_ubangi:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Sud-Ubangi");
                break;
            case CongoProvinces.nord_ubangi:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Nord-Ubangi");
                break;
            case CongoProvinces.mongala:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Mongala_(province)");
                break;
            case CongoProvinces.bas_uele:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Bas-Uele");
                break;
            case CongoProvinces.haut_uele:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Uele");
                break;
            case CongoProvinces.ituri:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Ituri");
                break;
            case CongoProvinces.nord_kivu:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Nord-Kivu");
                break;
            case CongoProvinces.sud_kivu:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Sud-Kivu");
                break;
            case CongoProvinces.maniema:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Maniema");
                break;
            case CongoProvinces.sankuru:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Sankuru_(province)");
                break;
            case CongoProvinces.mai_ndombe:
                webview.loadUrl("file:///android_asset/mai_ndombe.html");
                break;
            case CongoProvinces.tshuapa:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Mai-Ndombe_(province)");
                break;
            case CongoProvinces.kasai:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Kasaï_(province)");
                break;
            case CongoProvinces.kwilu:
                webview.loadUrl("file:///android_asset/kwilu.html");
                break;
            case CongoProvinces.kasai_central:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Kasaï-Central");
                break;
            case CongoProvinces.kasai_oriental:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Kasaï-Oriental");
                break;
            case CongoProvinces.lomami:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Lomami_(province)");
                break;
            case CongoProvinces.haut_lomami:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Lomami");
                break;
            case CongoProvinces.tanganyka:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Tanganyika_(province)");
                break;
            case CongoProvinces.haut_katanga:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Katanga");
                break;
            case CongoProvinces.lualaba:
                webview.loadUrl("https://fr.wikipedia.org/wiki/Lualaba_(province)");
                break;
            case CongoProvinces.kwango:
                webview.loadUrl("file:///android_asset/kwango.html");
                break;
            case CongoProvinces.kongo_central:
                webview.loadUrl("file:///android_asset/kongo_central.html");
                break;
            default:
                System.out.println("color not recognized !!!!!!!!!!!!!!!!!!!!!!!!!!" + this.getIntent().getIntExtra("ACTIVITY", -1));
                break;
        }
    }
    // endregion
}

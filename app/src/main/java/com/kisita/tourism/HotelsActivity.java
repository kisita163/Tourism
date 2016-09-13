package com.kisita.tourism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class HotelsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout demoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        demoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> pic_files = new HashMap<String, Integer>();

        pic_files.put("1", R.drawable.pic_kin);
        pic_files.put("2", R.drawable.pic_kin1);
        pic_files.put("3", R.drawable.pic_kin2);



        for(String name : pic_files.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(pic_files.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            demoSlider.addSlider(textSliderView);
        }
        demoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        demoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        demoSlider.setCustomAnimation(new DescriptionAnimation());
        demoSlider.setDuration(4000);
        demoSlider.addOnPageChangeListener(this);

        // region spinner
        int communes = arrayInitializer();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
           communes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //endregion
    }

    private int arrayInitializer() {
        int array  = 0;
        switch (this.getIntent().getIntExtra("ACTIVITY", -1)) {
            case CongoProvinces.kinshasa:
                array = R.array.kinshasa;
                break;
            case CongoProvinces.tshopo:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Tshopo_(province)");
                break;
            case CongoProvinces.sud_ubangi:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Sud-Ubangi");
                break;
            case CongoProvinces.nord_ubangi:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Nord-Ubangi");
                break;
            case CongoProvinces.mongala:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Mongala_(province)");
                break;
            case CongoProvinces.bas_uele:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Bas-Uele");
                break;
            case CongoProvinces.haut_uele:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Uele");
                break;
            case CongoProvinces.ituri:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Ituri");
                break;
            case CongoProvinces.nord_kivu:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Nord-Kivu");
                break;
            case CongoProvinces.sud_kivu:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Sud-Kivu");
                break;
            case CongoProvinces.maniema:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Maniema");
                break;
            case CongoProvinces.sankuru:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Sankuru_(province)");
                break;
            case CongoProvinces.mai_ndombe:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Mai-Ndombe_(province)");
                break;
            case CongoProvinces.tshuapa:
               // webview.loadUrl("https://fr.wikipedia.org/wiki/Mai-Ndombe_(province)");
                break;
            case CongoProvinces.kasai:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Kasaï_(province)");
                break;
            case CongoProvinces.kwilu:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Kwilu_(province)");
                break;
            case CongoProvinces.kasai_central:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Kasaï-Central");
                break;
            case CongoProvinces.kasai_oriental:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Kasaï-Oriental");
                break;
            case CongoProvinces.lomami:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Lomami_(province)");
                break;
            case CongoProvinces.haut_lomami:
               // webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Lomami");
                break;
            case CongoProvinces.tanganyka:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Tanganyika_(province)");
                break;
            case CongoProvinces.haut_katanga:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Haut-Katanga");
                break;
            case CongoProvinces.lualaba:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Lualaba_(province)");
                break;
            case CongoProvinces.kwango:
                //webview.loadUrl("https://fr.wikipedia.org/wiki/Kwango_(province)");
                break;
            case CongoProvinces.kongo_central:
                //webview.loadUrl("http://www.kongocentral.net/");
                break;
            default:
                System.out.println("color not recognized !!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;

        }
        return R.array.kinshasa;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}

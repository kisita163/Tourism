package com.kisita.tourism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.kisita.tourism.util.LocationsAdapter;
import com.kisita.tourism.util.XmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class FormActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout demoSlider;
    private Button button;
    private XmlParser xmlParser;
    private List<XmlParser.Entry> provinces = null;
    private InputStream stream = null;
    private XmlParser.City city;
    private XmlParser.Town town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        demoSlider = (SliderLayout)findViewById(R.id.slider);
        button = (Button)findViewById(R.id.button);
        xmlParser = new XmlParser();

        // region load data from xml
        try {
            loadXmlFromAssets();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XmlParser.Entry entry = getLocationData();

        // endregion

        // region button
        button .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this, MapActivity.class);
                intent.putExtra("latitude",town.getLatitude());
                intent.putExtra("longitude",town.getLongitude());
                startActivity(intent);
            }
        });

        // endregion

        // region slider

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

        // endregion

        // region spinner ville

        Spinner spinner = (Spinner) findViewById(R.id.ville);
        // Create an ArrayAdapter using the string array and a default spinner layout
        LocationsAdapter adapter = new LocationsAdapter(this,entry.getCities());
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Spinner","City selected");
                city =  getLocationData().getCities().get(position);
                // region spinner commune
                Spinner spinner = (Spinner) findViewById(R.id.commune);
                // Create an ArrayAdapter using the string array and a default spinner layout
                LocationsAdapter adapter = new LocationsAdapter(FormActivity.this, getLocationData().getCities().get(position).getTowns());
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("Spinner","Town selected");
                        town = city.getTowns().get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //endregion
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion
    }

    private XmlParser.Entry entriesIterator(String provinceName)
    {
        XmlParser.Entry entry = null;

        for(XmlParser.Entry e: provinces) // iterate on province
        {
            Log.i("Locations","province "+e.getName());
            if(e.getName().toLowerCase().equals(provinceName))
            {
                Log.i("Locations","province found is "+e.getName());
                entry = e;
                break;
            }
        }

        return entry;
    }

    private XmlParser.Entry getLocationData() {
        XmlParser.Entry entry = null;
        switch (this.getIntent().getIntExtra("ACTIVITY", -1)) {
            case CongoProvinces.kinshasa:
                entry = entriesIterator("kinshasa");
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
                entry = entriesIterator("sud-kivu");
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
                entry = entriesIterator("kwilu");
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
        return entry;
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

    private void loadXmlFromAssets()throws XmlPullParserException, IOException{
        try {
            InputStream stream  = getAssets().open("provinces.xml");
            provinces = xmlParser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

}

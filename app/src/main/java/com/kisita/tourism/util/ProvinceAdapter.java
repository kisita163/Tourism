package com.kisita.tourism.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kisita.tourism.R;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Hugues on 04/11/2016.
 */
public class ProvinceAdapter extends ArrayAdapter {
    private Context context;
    static final public Integer[] pImages = {R.drawable.bas_uele,
                                R.drawable.equateur,
                                R.drawable.haut_uele,
                                R.drawable.haut_katanga,
                                R.drawable.haut_lomami,
                                R.drawable.ituri,
                                R.drawable.kasai,
                                R.drawable.kasai_central,
                                R.drawable.kasai_oriental,
                                R.drawable.kinshasa,
                                R.drawable.kongo,
                                R.drawable.kwilu,
                                R.drawable.kwango,
                                R.drawable.lomami,
                                R.drawable.lualaba,
                                R.drawable.mai_ndombe,
                                R.drawable.maniema,
                                R.drawable.mongala,
                                R.drawable.nord_kivu,
                                R.drawable.nord_ubangi,
                                R.drawable.sankuru,
                                R.drawable.sud_kivu,
                                R.drawable.sud_ubangi,
                                R.drawable.tanganyka,
                                R.drawable.tshopo,
                                R.drawable.tshuapa
                              };


    static final public String[] pNames = {  "Bas-Uele",
                                "Equateur",
                                "Haut-Uele",
                                "Haut-Katanga",
                                "Haut-Lomami",
                                "Ituri",
                                "Kasaï",
                                "Kasaï-Central",
                                "Kasaï-Oriental",
                                "Kinshasa",
                                "Kongo-Central",
                                "Kwilu",
                                "Kwango",
                                "Lomami",
                                "Lualaba",
                                "Maï-Ndombe",
                                "Maniema",
                                "Mongala",
                                "Nord-Kivu",
                                "Nord-Ubangi",
                                "Sankuru",
                                "Sud-Kivu",
                                "Sud-Ubangi",
                                "Tanganyka",
                                "Tshopo",
                                "Tshuapa"
                                };


    public ProvinceAdapter(Context context) {
        super(context,0);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return pImages[position];
    }

    @Override
    public int getCount() {
        return pNames.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if(row == null) {
            //System.out.println("ConvertView is not provided");
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(R.layout.province_adapter,null);
        }



            TextView  pName = (TextView)row.findViewById(R.id.provinceView);
            ImageView pImage = (ImageView)row.findViewById(R.id.imageView7);

            pImage.setBackgroundColor(generateColor());
            pName.setText(pNames[position]);

        return row;
    }



    public int generateColor()
    {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return (color);
    }
}

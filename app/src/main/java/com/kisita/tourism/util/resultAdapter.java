package com.kisita.tourism.util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kisita.tourism.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Hugues on 08/11/2016.
 */
public class ResultAdapter extends ArrayAdapter {
    private Context context;
    private List<Place> places;
    public ResultAdapter(Context context,List<Place> places) {
        super(context,0,places);
        this.context = context;
        this.places = places;
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
        if(places.size() > 0) {
            pName.setText(places.get(position).name);
            Log.i(getClass().getName(),places.get(position).name);
        }

        return row;
    }
    public int generateColor()
    {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return (color);
    }
}

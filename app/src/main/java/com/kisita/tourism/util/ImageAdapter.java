package com.kisita.tourism.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kisita.tourism.R;

/**
 * Created by Hugues on 17/10/2016.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    public Integer[] mThumbIds = {
            R.drawable.h5,R.drawable.h2,
            R.drawable.h3,R.drawable.h4,
            R.drawable.h6,R.drawable.h5,
            R.drawable.h2,R.drawable.h4
    };

    public String[] mText = {"Restauration","Logements","Evénements","Culture","Santé","Loisirs","Bien-être","Transport"};

    public ImageAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        Typeface face= Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Bold.ttf");

        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        row = inflater.inflate(R.layout.activity_grid_item,null);


        ((TextView)row.findViewById(R.id.textView)).setText(mText[position]);
        ((TextView)row.findViewById(R.id.textView)).setTypeface(face);
        ((TextView)row.findViewById(R.id.textView)).setTextSize(21);
        ((TextView)row.findViewById(R.id.textView)).setTextColor(Color.WHITE);


        ((ImageView)row.findViewById(R.id.imageView)).setImageResource(mThumbIds[position]);
        ((ImageView)row.findViewById(R.id.imageView)).setScaleType(ImageView.ScaleType.CENTER_CROP);
        row.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, 400));


        return row;
    }
}

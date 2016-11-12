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
 * Created by Hugues on 12/11/2016.
 */
public class TransportListAdapter extends ArrayAdapter {
    private Context context;

    public TransportListAdapter(Context context) {
        super(context,0,TransportData.lines);
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        Log.i (getClass().getName(),"Length = "+TransportData.lines.length);

        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(R.layout.transport_adapter,null);
        }
        TextView terminus1 = (TextView)row.findViewById(R.id.terminus1);
        TextView terminus2 = (TextView)row.findViewById(R.id.terminus2);
        ImageView transportImage = (ImageView)row.findViewById(R.id.transportImage);

        transportImage.setBackgroundColor(generateColor());

        terminus1.setText(TransportData.lines[position][1]);
        terminus2.setText(TransportData.lines[position][2]); //TODO // what if distance is null
        return row;
    }
    public int generateColor()
    {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return (color);
    }
}

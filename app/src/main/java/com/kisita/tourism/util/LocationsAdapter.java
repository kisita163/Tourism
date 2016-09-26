package com.kisita.tourism.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kisita.tourism.R;

import java.util.List;
import java.util.Objects;

/**
 * Created by Hugues on 23/09/2016.
 */
public class LocationsAdapter extends ArrayAdapter{
    public LocationsAdapter(Context context,List cities) {
        super(context, 0, cities);
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return initView(position, convertView);
    }

    private View initView(int position, View convertView) {
        if(convertView == null)
            convertView = View.inflate(getContext(),
                    R.layout.row_locations,
                    null);
        TextView textView = (TextView)convertView.findViewById(R.id.location);
        if(getItem(position) instanceof XmlParser.City)
            textView.setText(((XmlParser.City)getItem(position)).getName());
        if(getItem(position) instanceof XmlParser.Town)
            textView.setText(((XmlParser.Town)getItem(position)).getName());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_locations,parent, false);
        }


        LocationViewHolder viewHolder = (LocationViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new LocationViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.location);
            //viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Object location = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        if(getItem(position) instanceof XmlParser.City)
            viewHolder.pseudo.setText(((XmlParser.City)location).getName());
        if(getItem(position) instanceof XmlParser.Town)
            viewHolder.pseudo.setText(((XmlParser.Town)location).getName());
       // viewHolder.avatar.setImageDrawable(new ColorDrawable(Color.BLACK));

        return convertView;
    }
    // region inner classes
    class LocationViewHolder{
        public TextView pseudo;
        public ImageView avatar;
    }
    class Location {
        private int color;
        private String pseudo;

        public Location(int color, String pseudo) {
            this.color = color;
            this.pseudo = pseudo;
        }

        public int getColor() {
            return color;
        }

        public String getPseudo() {
            return pseudo;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }

        public void setColor(int color) {
            this.color = color;
        }
        //...setters
    }

    // endregion


}

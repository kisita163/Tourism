package com.kisita.tourism.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.kisita.tourism.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugues on 03/10/2016.
 */
public class PlacesFinder {
    private static final String PLACE_URL_API = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyCL47Bv1Be2IgChsrYs5cbcQ3oLQpljh7w";
    private String latitude;
    private String longitude;
    private String type;
    private PlacesFinderListener listener;
    private List<Place> places;
    private boolean hasNextPageToken = false;
    private char pageNumber = 0;
    private String token="";
    private boolean dialog = false;


    public PlacesFinder(double latitude, double longitude, String type , PlacesFinderListener listener ) {
        this.latitude = Double.toString(latitude);
        this.longitude = Double.toString(longitude);
        this.type = type;
        this.listener = listener;
        places = new ArrayList<>();
    }

    public void execute() throws UnsupportedEncodingException {
        if(!dialog)
        {
            listener.onDirectionFinderStart();
            dialog = true;
        }
        new DownloadRawData().execute(createUrl());
    }

    private String createUrl() throws UnsupportedEncodingException {
        String urlLat;
        String urlLng;
        String urlType;
        if(latitude != null || longitude != null || type != null) {
             urlLat = URLEncoder.encode(latitude, "utf-8");
             urlLng = URLEncoder.encode(longitude, "utf-8");
             urlType = URLEncoder.encode(type, "utf-8");
        }else{
            urlLat = URLEncoder.encode("-4349114", "utf-8");
            urlLng = URLEncoder.encode("15292118", "utf-8");
            urlType = URLEncoder.encode("lodging", "utf-8");
        }

        String toReturn = PLACE_URL_API + "location=" + urlLat +"," + urlLng + "&radius=30000" + "&type="+urlType;

        if(hasNextPageToken){
            toReturn += "&pagetoken="+token;
        }
        toReturn += "&key="+ GOOGLE_API_KEY;

        Log.i(getClass().getName(), "URL = " + toReturn);

        return  toReturn;
    }

    private class DownloadRawData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String link = params[0];
            try {
                URL url = new URL(link);
                InputStream is = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i(getClass().getName(),line);
                    buffer.append(line + "\n");
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseJSon(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseJSon(String data) throws JSONException {
        if (data == null)
            return;
        JSONObject jsonData = new JSONObject(data);
        //String jsonNextToken = jsonData.getString("next_page_token");
        JSONArray jsonResults = jsonData.getJSONArray("results");
        Log.i(getClass().getName(),"length="+jsonResults.length()+ " " + hasNextPageToken);
        if(jsonResults.length() == 0 && hasNextPageToken){
            try {
                this.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < jsonResults.length(); i++) {
            JSONObject jsonPlace = jsonResults.getJSONObject(i);
            Place place = new Place();

            JSONObject geometry = jsonPlace.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            String vicinity = jsonPlace.getString("vicinity");
            String name = jsonPlace.getString("name");

            place.name = name;//name.getString("text");
            place.latitude = location.getDouble("lat");
            place.longitude = location.getDouble("lng");
            place.vicinity = vicinity;//vicinity.getString("text");

            Log.i(getClass().getName(), "name = " + place.name);

            places.add(place);
        }
        if(jsonData.has("next_page_token")) {
            Log.i(getClass().getName(), jsonData.getString("next_page_token"));
            hasNextPageToken=true;
            token = jsonData.getString("next_page_token");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                this.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }else {
            hasNextPageToken=false;
            Log.i(getClass().getName(), "stop progress dialog");
            listener.onDirectionFinderSuccess(places);
        }
    }
}

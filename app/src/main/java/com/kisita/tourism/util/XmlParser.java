package com.kisita.tourism.util;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugues on 17/09/2016.
 */
public class XmlParser {
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readData(parser);
        } finally {
            in.close();
        }
    }

    private List readData(XmlPullParser parser) throws XmlPullParserException, IOException {
        List provinces = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "pays"); // Test if the current event is of the given type and if the namespace and name do match.
        int pNumber = 4;
        int k = 0;

        while(pNumber > 0)
        {
            if(parser.next() == XmlPullParser.START_TAG  && parser.getName().equals("province"))
            {
                Log.i("province","province = " + parser.getAttributeValue(0));
                provinces.add(readProvince(parser));
                pNumber--;
            }
        }

        return provinces;
    }

    // region class to put elsewhere

    public static class Town{
        private String name;

        public Town(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public static class  City{
        private double longitude = 0;
        private double latitude = 0;

        public double getLongitude() {
            return longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        private String name;
        private  List<Town> towns = null; // Une ville compte des communes

        public City(String name,List<Town> towns)
        {
            this.name = name;
            this.towns = towns;
        }

        public City(String name, double latitude, double longitude, List<Town> towns) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.towns = towns;
        }

        public String getName() {
            return name;
        }

        public List<Town> getTowns() {
            return towns;
        }
    }

    public static class Entry {
        private String name = "";
        private List<City> cities = null; // Une liste de villes contient des villes


        private Entry(String name) {
            this.name = name;
        }

        public void print()
        {
            System.out.println("");
            System.out.println("!!!!!!!!! "+this.getName()+" !!!!!!!!!");
            System.out.println("");
            for(City c:cities)
            {
                System.out.println("------> " + c.getName());
                for(Town t:c.getTowns())
                {
                    System.out.println("* " + t.getName());
                    System.out.println("** " + c.getLatitude() + " " + c.getLongitude());
                }
            }
        }

        public String getName() {
            return name;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }

        public List<City> getCities() {
            return cities;
        }
    }

    // endregion

    private Entry readProvince(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "province");

        Entry province  = new Entry(parser.getAttributeValue(0)); // get the province name
        List<City> cities = new ArrayList<>();

        while(true)
        {
            parser.next();

            if(parser.getName() != null ) {
                if(parser.getName().equals("ville") || parser.getName().equals("district") ){
                    if (parser.getEventType() == XmlPullParser.START_TAG) {
                        Log.i("ville", "ville/district = " + parser.getAttributeValue(0));
                        // get the list of town
                        cities.add(new City(parser.getAttributeValue(0),
                                            Double.parseDouble(parser.getAttributeValue(1)),
                                            Double.parseDouble(parser.getAttributeValue(2)),
                                            readCity(parser)));
                        // process cities and then add them to the province
                        province.setCities(cities);
                    }
                }

                if (parser.getName().equals("province")) {
                    Log.i("ville", "end of province");
                    break;
                }
            }
        }
        //province.print();
        return province;
    }

    private List<Town> readCity(XmlPullParser parser) throws XmlPullParserException, IOException {

        List<Town> towns = new ArrayList<>();

        while(true) {
            parser.next();
            if (parser.getName() != null) {
                if (parser.getName().equals("commune") || parser.getName().equals("territoire")) {
                    if (parser.getEventType() == XmlPullParser.START_TAG) {
                        Log.i("communes", "commune/territoire = " + parser.getAttributeValue(0));
                        towns.add(readTown(parser.getAttributeValue(0),parser));

                    }
                }
                if (parser.getName().equals("ville") || parser.getName().equals("district")) {
                    Log.i("communes", "end of ville/district");
                    break;
                }
            }
        }
        return towns;
    }

    private Town readTown(String name,XmlPullParser parser) throws XmlPullParserException, IOException {

        Town town = new Town(name);
        return town;
    }
}

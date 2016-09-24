package com.kisita.tourism.util;

/**
 * Created by Hugues on 24/09/2016.
 */
public class DBEntry {
    private String name;
    private double latitude;
    private double longitude;
    private String province;
    private String description;


    public DBEntry(String name, double latitude, double longitude, String description, String province) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getProvince() {
        return province;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }
}

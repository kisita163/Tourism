package com.kisita.tourism.util;

import java.util.Date;

/**
 * Created by Hugues on 24/09/2016.
 */
public class Event extends DBEntry {
    private Date date;
    public Event(String name,String type, double latitude, double longitude, String description, String province,Date date) {
        super(name,type,latitude, longitude, description, province);
        this.date = date;
    }
}

package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class places_visited {
    @PrimaryKey
    private int id;

    @ColumnInfo(name="place_name")
    private String place_name;

    @ColumnInfo(name="date" )
    private String date;

    @ColumnInfo(name="time" )
    private String time;

    @ColumnInfo(name="checkin/checkout" )
    private String checkinorout;

    @ColumnInfo(name="customText" )
    private String customText;


    public int getId() {
        return id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getDate() {
        return date;
    }

    public String getCustomText() {
        return customText;
    }

    public String getTime() {
        return time;
    }

    public String getCheckinorout() {
        return checkinorout;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCheckinorout(String checkinorout) {
        this.checkinorout = checkinorout;
    }

    public void setCustomText(String customText) {
        this.customText = customText;
    }
}


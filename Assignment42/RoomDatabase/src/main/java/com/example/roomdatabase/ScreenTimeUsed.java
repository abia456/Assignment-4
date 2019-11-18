package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ScreenTimeUsed {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String ScreenEevnt;

    @ColumnInfo
    private int count;

    @ColumnInfo
    private String date;

    @ColumnInfo
    private String time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreenEevnt() {
        return ScreenEevnt;
    }

    public void setScreenEevnt(String screenEevnt) {
        ScreenEevnt = screenEevnt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

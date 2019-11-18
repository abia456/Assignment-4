package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface PlacesVisitedDoa {

    @Insert
    public void addPlace(places_visited pv);
}

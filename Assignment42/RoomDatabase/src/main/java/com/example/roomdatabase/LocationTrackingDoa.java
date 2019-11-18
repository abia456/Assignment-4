package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface LocationTrackingDoa {

    @Insert
    public void addloacationtrack (LocationTracking lk);
}

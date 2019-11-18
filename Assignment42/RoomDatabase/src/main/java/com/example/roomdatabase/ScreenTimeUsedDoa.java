package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ScreenTimeUsedDoa {
    @Insert
    public void addScreenTime(ScreenTimeUsed st);
}


package com.example.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {places_visited.class,LocationTracking.class,ScreenTimeUsed.class},version = 2)
public  abstract class MyDB extends RoomDatabase {


    public abstract PlacesVisitedDoa doa();
    public abstract LocationTrackingDoa doa2();
    public abstract ScreenTimeUsedDoa doa3();

    private static final String DB_NAME="MY Database";
    private static  MyDB mydb;

    public static synchronized MyDB getInstance(Context context)
    {
        if(mydb==null)
        {
            mydb=Room.databaseBuilder(context.getApplicationContext(),MyDB.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return  mydb;
    }

}

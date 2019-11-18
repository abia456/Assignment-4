package com.example.screentime;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.roomdatabase.LocationTracking;
import com.example.roomdatabase.LocationTrackingDoa;
import com.example.roomdatabase.MyDB;
import com.example.roomdatabase.ScreenTimeUsed;
import com.example.roomdatabase.ScreenTimeUsedDoa;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenTIme extends Service {

    int count=0;
    MyDB mydb;
    ScreenTimeUsedDoa Sdoa;

////////////////////////////////////////////////////////////////////////////
private BroadcastReceiver mScreenStateBroadcastReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            count++;
            //screentime_used s= new  screentime_used(true,count);
            ScreenTimeUsed stu= new ScreenTimeUsed();
            //Set
            stu.setCount(count);
            stu.setScreenEevnt("Screen on");
            Date d=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
            String time = sdf.format(d);
            stu.setTime(time);
            stu.setDate(d.toString());

            insert(stu);

            //inser
            // t(s);

        }

        else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            //count++;
            //screentime_used s= new  screentime_used(false,count);
            //insert(s);
            count++;
            //screentime_used s= new  screentime_used(true,count);
            ScreenTimeUsed stu= new ScreenTimeUsed();
            //Set
            stu.setCount(count);
            stu.setScreenEevnt("Screen off");
            Date d=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
            String time = sdf.format(d);
            stu.setTime(time);
            stu.setDate(d.toString());

            insert(stu);



        }
    }
};


    ///////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate() {
        super.onCreate();


        mydb=MyDB.getInstance(this);
        Sdoa=mydb.doa3();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStateBroadcastReceiver, intentFilter);
    }
//////////////////////////////////////////////////////////////////////////////////
@Override
public int onStartCommand(Intent intent, int flags, int startId) {

    return super.onStartCommand(intent, flags, startId);
}
////////////////////////////////////////////////////////////////




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
///////////////////////////////////////////////////
@Override
public void onDestroy() {
    unregisterReceiver(mScreenStateBroadcastReceiver);
    super.onDestroy();
}
////////////////////////////////////////////////////


    public void insert(ScreenTimeUsed lt)
    {
        new InsertAsyncTask(Sdoa).execute(lt);
        Toast t= Toast.makeText(getApplication().getApplicationContext(), "Inserted",Toast.LENGTH_SHORT);
        t.show();
    }

    public static class InsertAsyncTask extends AsyncTask<ScreenTimeUsed,Void,Void> {
        ScreenTimeUsedDoa mAsyncTaskDoa;

        public InsertAsyncTask(ScreenTimeUsedDoa mAsyncTaskDoa) {
            this.mAsyncTaskDoa = mAsyncTaskDoa;
        }


        @Override
        protected Void doInBackground(ScreenTimeUsed... screenTimeUseds) {
            mAsyncTaskDoa.addScreenTime(screenTimeUseds[0]);
            return null;
        }
    }

    ////////////////////////////////////////////////


}

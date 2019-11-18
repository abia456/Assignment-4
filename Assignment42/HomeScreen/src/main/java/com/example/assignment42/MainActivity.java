package com.example.assignment42;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.roomdatabase.LocationTrackingDoa;
import com.example.roomdatabase.MyDB;

import android.os.AsyncTask;
import android.os.Bundle;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;

import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.location_module.GPS_Service;
import com.example.roomdatabase.LocationTracking;
import com.example.screentime.ScreenTIme;
import com.facebook.stetho.Stetho;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Context c= this;
    private Button btn_start, btn_stop;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;

    MyDB mydb;
    LocationTrackingDoa ltDoa;
    private BroadcastReceiver broadcastReceiver;
    int id=0;

/////////////////////////////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {


                    //textView.setText("\n" +intent.getExtras().get("longitude"));

                    textView.setText(intent.getExtras().get("longitude").toString());

                    textView1.setText("\n" +intent.getExtras().get("latitude"));

                    //textView2.append("\n" +intent.getExtras().get("Address"));

                  textView2.append("\n" +intent.getExtras().get("Accuracy"));


                    String dateStr= "04/09/2010";
                    SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
                    Date c= new Date();
                    String formattedDate= format.format(c);
                    //Toast t= Toast.makeText(getApplication().getApplicationContext(), c.toString(),Toast.LENGTH_SHORT);
                    //t.show();

                    LocationTracking lt= new LocationTracking();

                    lt.setLatitude(intent.getExtras().get("latitude").toString());
                    lt.setLongitude(intent.getExtras().get("longitude").toString());
                    lt.setAccuracy(intent.getExtras().get("Accuracy").toString());
                    lt.setDate(c.toString());
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String time = sdf.format(d);
                    lt.setTime(time);

                    //ltDoa.addloacationtrack(lt);
                    insert(lt);





                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }



//////////////////////////////////////////////////////////////////







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);



        btn_start= findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        textView = (TextView) findViewById(R.id.textView);
        textView1= findViewById(R.id.textView2);
        textView2= findViewById(R.id.textView3);

        mydb= MyDB.getInstance(this);
        ltDoa= mydb.doa2();


        if(!runtime_permissions())
            enable_buttons();


        /////////////////////////////////////////////////
    }

    /////////////////////////////////////////////
    private void enable_buttons() {

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //count=1; //to mark starting location
                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                startService(i);

                Intent i2= new Intent(getApplicationContext(), ScreenTIme.class);
                startService(i2);

            }
        });


        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                stopService(i);

                Intent i2= new Intent(getApplicationContext(), ScreenTIme.class);
                stopService(i2);

            }
        });

    }

    ////////////////////////////////////////

    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else {
                runtime_permissions();
            }
        }
    }





///////////////////////////////////////////////////

    public void insert(LocationTracking lt)
    {
        new InsertAsyncTask(ltDoa).execute(lt);
        Toast t= Toast.makeText(getApplication().getApplicationContext(), "Inserted",Toast.LENGTH_SHORT);
        t.show();

    }

    public static class InsertAsyncTask extends AsyncTask<LocationTracking,Void,Void>{
        LocationTrackingDoa mAsyncTaskDoa;

        public InsertAsyncTask(LocationTrackingDoa mAsyncTaskDoa) {
            this.mAsyncTaskDoa = mAsyncTaskDoa;
        }

        @Override
        protected Void doInBackground(LocationTracking... locationTrackings) {
            mAsyncTaskDoa.addloacationtrack(locationTrackings[0]);

            return null;
        }
    }

 ///////////////////////////////


}

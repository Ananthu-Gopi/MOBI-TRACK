package com.lanciar.app.mobitrack;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class locations extends Service {

    private GoogleApiClient mGoogleApiClient;
    int x=0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("yyyyyyy", "service");


        final ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();

         final Runnable periodicTask = new Runnable() {
            public void run() {
            if(x==0) {
                loc l = new loc();
                l.getLoc();
            }
                else
            {
                executor.shutdown();

            }
            }
        };

        executor.scheduleAtFixedRate(periodicTask, 0, 10, TimeUnit.SECONDS);


        return super.onStartCommand(intent, flags, startId);
    }


    public class loc implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

        private static final int MILLISECONDS_PER_SECOND = 1000;

        LocationRequest mLocationRequest;

        public void getLoc() {

            Log.d("yyyyyyy", "loc");
            if (mGoogleApiClient == null) {
                mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();
            }
            mGoogleApiClient.connect();
//            mLocationRequest = LocationRequest.create();
//            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//            mLocationRequest.setInterval(5);
//            mLocationRequest.setFastestInterval(5);
//
//            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//
//
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d("yyyyyyy","connectedddddddd");
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            Log.d("yyyyyyy",""+mLastLocation);
            //Toast.makeText(getApplicationContext(),""+mLastLocation,Toast.LENGTH_LONG).show();
        loc_service ll=new loc_service();

            SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
            String restoredText = prefs.getString("id", null);
            if (restoredText != null) {
                String ss = "insert into tbl_location(emp_id,latitude,longitude,date)values('"+restoredText+"','" + mLastLocation.getLatitude() + "','" + mLastLocation.getLongitude() + "',curdate())";
                ll.execute(ss);
            }
            mGoogleApiClient.disconnect();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    @Override
//    public void onLocationChanged(Location location) {
//        Toast.makeText(getApplicationContext(),"changed"+location,Toast.LENGTH_LONG).show();
//    }
}

    @Override
    public void onDestroy() {
        super.onDestroy();
        x=1;
        Log.d("yyyyyyy","dessssssssssssstryyy");
    }
}
package com.lanciar.app.mobitrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

public class location_activity extends AppCompatActivity {


    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_activity);

        Intent intent=new Intent(this,locations.class);
        startService(intent);
        Log.d("yyyyyyy","mmmmmmm");


    }

}

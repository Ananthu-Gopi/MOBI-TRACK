package com.lanciar.app.mobitrack;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        View.OnClickListener {
    //Our Map
    private GoogleMap mMap;
    //To store longitude and latitude from map
    private double longitude;
    private double latitude;
    private ImageButton buttonSave;
    private ImageButton buttonCurrent;
    private ImageButton buttonView;
    String name="";
    String latitudes[]=null;
    String longitudes[]=null;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Initializing googleapi client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        //Initializing views and adding onclick listeners
//        buttonSave = (ImageButton) findViewById(R.id.buttonSave);
        buttonCurrent = (ImageButton) findViewById(R.id.buttonCurrent);
//        buttonView = (ImageButton) findViewById(R.id.buttonView);
//        buttonCurrent.setOnClickListener(this);
        buttonCurrent.setOnClickListener(this);
//        buttonView.setOnClickListener(this);
        name=getIntent().getStringExtra("name");
    }
    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }
    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    }
    //Function to move the map
    private void moveMap(double latitude,double longitude) {
        //String to display current latitude and longitude
        String msg = latitude + ", "+longitude;

        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude,longitude);

        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(false) //Making the marker draggable
                .title("Current Location")); //Adding a title
        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        //Displaying current coordinates in toast
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void moveMap(LatLng lng) {
        //String to display current latitude and longitude
//        String msg = latitude + ", "+longitude;
        //Creating a LatLng Object to store Coordinates
//        LatLng latLng = new LatLng(latitude, longitude);
        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(lng) //setting position
                .draggable(true) //Making the marker draggable
                .title("Current Location")); //Adding a title
        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lng));
        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //Displaying current coordinates in toast
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);
    }
    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
    }
    @Override
    public void onConnectionSuspended(int i) {}
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        //Clearing all the markers
        mMap.clear();
        //Adding a new marker to the current pressed position
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));
    }
    @Override
    public void onMarkerDragStart(Marker marker) {}
    @Override
    public void onMarkerDrag(Marker marker) {}

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //Getting the coordinates
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //Moving the map
       // moveMap();
    }
    @Override
    public void onClick(View v) {
        if(v == buttonCurrent){
            JSONArray cast = null;
            String s=getIntent().getStringExtra("val");
            try {
                cast = new JSONArray(s);
                latitudes = new String[cast.length()] ;
                longitudes = new String[cast.length()];

                int i = 0;
                for (int j = 0; j < cast.length(); j++, i++) {
                    JSONObject actor = cast.getJSONObject(j);
                    latitudes[i] = actor.getString("latitude");
                    longitudes[i] = actor.getString("longitude");
                    moveMap(Double.parseDouble(latitudes[i]),Double.parseDouble(longitudes[i]));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            Intent i=new Intent(this,locations.class);
//            startService(i);



//            if(name.equals("Cricket"))
//            {
//                moveMap(9.8930, 76.7221);
//                moveMap(9.9894, 76.5790);
//                moveMap(9.9278, 76.6023);
//            }
//            else if(name.equals("Football"))
//            {
//                moveMap(10.0718, 76.5488);
//                moveMap(10.0602, 76.6351);
//
//            }
//            else if(name.equals("Volley ball"))
//            {
//                moveMap(10.1076, 76.3457);
//                moveMap(10.1319, 76.4822);
//                moveMap(10.1849, 76.3753);
//
//            }
//            else if(name.equals("Basket ball"))
//            {
//                moveMap(9.8731, 76.4920);
//                moveMap( 9.9783, 76.4739);
//                moveMap(9.9487, 76.3464);
//            }
        }
    }
}

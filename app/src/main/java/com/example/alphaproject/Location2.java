package com.example.alphaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class Location2 extends AppCompatActivity  {
    public static final int PERMISSIONS_FINE_LOCATION=99;
    /**
     * references to UI objects
     */
    TextView tv1,tv2;

    /**
     * Google's API for location services.
     */
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv1=(TextView) findViewById(R.id.textView);
        tv2=(TextView) findViewById(R.id.textView2);

        /**
         * Set all properties of locationRequest.
         */

        locationRequest=new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(100);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                }
                else{
                    Toast.makeText(this,"this apps requires permission to be granted in order to work properly",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    /**
     * get permissions from the user to track gps
     * get the current location from the fused client.
     * update the UI-i.e. set all properties in their associated text view items
     */
    private void updateGPS(){
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(Location2.this);

        /**
         * User provided permission
         */
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    /**
                     * we got permissions. put the values of location. xxx into the UI components.
                     */
                    updateUIValues(location);
                }
            });
        }
        else{
            /**
             *  permission not granted yet
             */
            if (Build.VERSION.SDK_INT>=23){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION} ,PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    /**
     * Update all of the text view objects with the new location.
     */
    private void updateUIValues(Location location) {
        tv1.setText("lat:"+String.valueOf(location.getLatitude()));
        tv2.setText("long:"+String.valueOf(location.getLongitude()));
    }

    /**
     * a button that starts the location finding in this moment.
     */
    public void ShowLocation (View view) {
        updateGPS();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * switch activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu){
        String st = menu.getTitle().toString();
        if ((st.equals("sms"))) {
            Intent si = new Intent(this, Sms.class);
            startActivity(si);
        }
        if ((st.equals("storage"))) {
            Intent si = new Intent(this, Storage.class);
            startActivity(si);
        }
        if ((st.equals("main"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("auth"))) {
            Intent si = new Intent(this, Auth.class);
            startActivity(si);
        }


        return true;

    } }












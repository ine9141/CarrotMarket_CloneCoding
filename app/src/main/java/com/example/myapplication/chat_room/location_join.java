package com.example.myapplication.chat_room;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.around;
import com.example.myapplication.chat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class location_join extends AppCompatActivity implements OnMapReadyCallback {

    double longitude;
    double latitude;
    private String x;
    private String y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_join);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        Intent secondIntent = getIntent();
        if (secondIntent.hasExtra("x")) {
            x = secondIntent.getStringExtra("x");
            longitude = Double.parseDouble(String.valueOf(x));
        }

        if (secondIntent.hasExtra("y")) {
            y = secondIntent.getStringExtra("y");
            latitude = Double.parseDouble(String.valueOf(y));
        }
        LatLng NOW = new LatLng(longitude, latitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(NOW);
        markerOptions.title("만날 장소");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(NOW));
        map.animateCamera(CameraUpdateFactory.zoomTo(17));
    }
}

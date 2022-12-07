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

public class location_share extends AppCompatActivity implements OnMapReadyCallback {

    double longitude;
    double latitude;
    double lat;
    double lon;
    private GoogleMap mMap;
    private String myName;
    private String otherName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_share);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent secondIntent = getIntent();
        if (secondIntent.hasExtra("myName")) {
            myName = secondIntent.getStringExtra("myName");
        }

        if (secondIntent.hasExtra("otherName")) {
            otherName = secondIntent.getStringExtra("otherName");
        }


        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(location_share.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            // 가장최근 위치정보 가져오기
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();

                FragmentManager fragmentManager = getFragmentManager();
                MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);


            }
        }
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        LatLng NOW = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(NOW);
        markerOptions.title("현재 위치");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(NOW));
        map.animateCamera(CameraUpdateFactory.zoomTo(17));


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
                mOptions.title("마커 좌표");
                lat = latLng.latitude; // 위도
                lon = latLng.longitude; // 경도
                // 마커의 스니펫(간단한 텍스트) 설정
                mOptions.snippet(lat + ", " + lon);
                // LatLng: 위도 경도 쌍을 나타냄
                mOptions.position(new LatLng(lat, lon));
                // 마커(핀) 추가

                mMap.addMarker(mOptions);

                LatLng SET = new LatLng(lat, lon);
                mOptions.position(SET);
                mOptions.title("공유할 위치");
                mMap.moveCamera(CameraUpdateFactory.newLatLng(SET));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                String url = "https://google.co.kr/maps/@"+marker.getPosition().latitude+","+marker.getPosition().longitude+",18z";
                Intent intent = new Intent(getApplicationContext(),chat_room_activity.class);
                intent.putExtra("myName",myName);
                intent.putExtra("otherName",otherName);
                intent.putExtra("URL",url);
                startActivity(intent);
                return false;
            }
        });


    }
}

package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class check_address extends AppCompatActivity {
    Button search;
    EditText dong;
    TextView text;
    String s,nickname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_address);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //닉네임 받아오기
        Intent secondIntent = getIntent();
        if (secondIntent.hasExtra("nick_name")) {
            nickname = secondIntent.getStringExtra("nick_name");
        }

        search = (Button) findViewById(R.id.search);
        dong = (EditText) findViewById(R.id.dong);
        text = (TextView) findViewById(R.id.text);

        // 위치 관리자 객체 참조하기
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(check_address.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION}, 0);

                } else {
                    // 가장최근 위치정보 가져오기
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (location != null) {
                        String provider = location.getProvider();
                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();

                        //provider : gps
                        //longitude : 위도값
                        //latitude : 경도값

                        Geocoder gCoder = new Geocoder(check_address.this, Locale.KOREA);

                        try {
                            Address a = gCoder.getFromLocation(latitude, longitude, 1).get(0);
                            dong.setText(a.getSubLocality()+' '+a.getThoroughfare());
                            text.setText(a.getSubLocality()+' '+a.getThoroughfare());
                            s = a.getThoroughfare();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),home.class);
                intent.putExtra("dong_s",s);
                intent.putExtra("nick_name",nickname);
                startActivity(intent);
            }
        });
    }
}

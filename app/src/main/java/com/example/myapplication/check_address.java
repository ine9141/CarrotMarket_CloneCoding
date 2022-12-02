package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class check_address extends AppCompatActivity {
    Button home, life, around, chat, my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        /*
            Geocoder gCoder = new Geocoder(ctx, Locale.getDefault());
            List<Address> addr = gCoder.getFromLocation(lat, lng, 1);
            Address a = addr.get(0);

            a.getAdminArea()+" "+a.getLocality()+" "+a.getThoroughfare()
         */


    }
}
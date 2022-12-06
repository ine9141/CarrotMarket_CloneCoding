package com.example.myapplication;
import com.example.myapplication.chat_room.chat_room_activity;
import com.example.myapplication.chat_room.location_share;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = new Intent(getApplicationContext(), login.class); //default : login.class
        startActivity(intent);

    }
}
package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class my extends AppCompatActivity {
    Button home, life, around, chat, my;
    TextView user_name;
    String s,nick_name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //하단 액션바
        home = (Button) findViewById(R.id.home);
        life = (Button) findViewById(R.id.life);
        around = (Button) findViewById(R.id.around);
        chat = (Button) findViewById(R.id.chat);
        my = (Button) findViewById(R.id.my);
        user_name = (TextView) findViewById(R.id.user_name);

        //동네이름 설정
        Intent secondIntent = getIntent();
        if (secondIntent.hasExtra("dong_s")) {
            s = secondIntent.getStringExtra("dong_s");
        }

        //닉네임 설정
        if (secondIntent.hasExtra("nick_name")) {
            nick_name = secondIntent.getStringExtra("nick_name");
            user_name.setText(nick_name);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                intent.putExtra("dong_s", s);
                intent.putExtra("nick_name", nick_name);
                startActivity(intent);
            }
        });

        life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), life.class);
                intent.putExtra("dong_s", s);
                intent.putExtra("nick_name", nick_name);
                startActivity(intent);
            }
        });

        around.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), around.class);
                intent.putExtra("dong_s", s);
                intent.putExtra("nick_name", nick_name);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), chat.class);
                intent.putExtra("dong_s", s);
                intent.putExtra("nick_name", nick_name);
                startActivity(intent);
            }
        });

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), my.class);
                intent.putExtra("dong_s", s);
                intent.putExtra("nick_name", nick_name);
                startActivity(intent);
            }
        });
    }
}
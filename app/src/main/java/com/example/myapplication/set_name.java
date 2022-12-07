package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class set_name extends AppCompatActivity {
    EditText nickname;
    Button next;
    String s;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_name);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        nickname = (EditText) findViewById(R.id.nickname);
        next = (Button) findViewById(R.id.next);

        //동네이름 설정
        Intent secondIntent = getIntent();
        if (secondIntent.hasExtra("dong_s")) {
            s = secondIntent.getStringExtra("dong_s");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),home.class);
                intent.putExtra("dong_s",s);
                intent.putExtra("nick_name",nickname.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
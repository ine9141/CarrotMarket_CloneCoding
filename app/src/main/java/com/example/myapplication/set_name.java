package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class set_name extends AppCompatActivity {
    EditText nickname;
    Button next;
    TextView notice;
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
        notice = (TextView) findViewById(R.id.notice);

        nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nickname.getText().toString().trim().length() >= 2) {
                    next.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.carrot));
                    notice.setText("프로필 사진과 닉네임을 입력해주세요.");
                    notice.setTextColor(Color.GRAY);
                    nickname.setBackgroundResource(R.drawable.set_name_bord);
                }
                else {
                    next.setBackgroundColor(Color.parseColor("#00FF0000"));
                }

            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nickname.getText().toString().trim().length() < 2){
                    notice.setText("닉네임은 2자 이상 입력해주세요.");
                    nickname.setBackgroundResource(R.drawable.set_name_board_change);
                    notice.setTextColor(Color.RED);
                }

                else {
                    Intent intent = new Intent(getApplicationContext(), check_address.class);
                    intent.putExtra("nick_name", nickname.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
    }
}
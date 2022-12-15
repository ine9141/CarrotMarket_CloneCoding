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

import com.example.myapplication.chat_room.chat_room_activity;

import org.w3c.dom.Text;

public class post_page extends AppCompatActivity {
    Button chatbtn;
    TextView title, text, price,view_name, dong, view_post_time;
    String num, txt, s, dong_name, nick_name, other_name, other_dong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_post);

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        chatbtn = (Button) findViewById(R.id.chatbtn);
        title = (TextView) findViewById(R.id.title);
        price = (TextView) findViewById(R.id.price);
        text = (TextView) findViewById(R.id.text);
        view_name = (TextView) findViewById(R.id.user_name);
        dong = (TextView) findViewById(R.id.dong_name);
        view_post_time = (TextView) findViewById(R.id.view_post_time);
        Intent secondIntent = getIntent();

        //동네이름 설정
        if (secondIntent.hasExtra("dong_s")) {
            dong_name = secondIntent.getStringExtra("dong_s");
        }

        //닉네임 설정
        if (secondIntent.hasExtra("nick_name")) {
            nick_name = secondIntent.getStringExtra("nick_name");
        }

        //게시자 동네 설정
        if (secondIntent.hasExtra("dong")) {
            other_dong = secondIntent.getStringExtra("dong");
            dong.setText(other_dong);
        }

        //게시자 닉네임 설정
        if (secondIntent.hasExtra("other")) {
            other_name = secondIntent.getStringExtra("other");
            view_name.setText(other_name);
        }

        //시간 설정
        if (secondIntent.hasExtra("other")) {
            view_post_time.setText(secondIntent.getStringExtra("time"));
        }



        //제목
        if (secondIntent.hasExtra("title")) {
            s = secondIntent.getStringExtra("title");
            title.setText(s);
        }

        //내용
        if (secondIntent.hasExtra("text")) {
            txt = secondIntent.getStringExtra("text");
            text.setText(txt);
        }

        //가격
        if (secondIntent.hasExtra("price")) {
            num = secondIntent.getStringExtra("price");
            price.setText(num);
        }

        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), chat_room_activity.class);
                intent.putExtra("dong_s", dong_name);
                intent.putExtra("myName", nick_name);
                intent.putExtra("title", s);
                intent.putExtra("text", txt);
                intent.putExtra("price", num);
                intent.putExtra("otherName", other_name);
                startActivity(intent);
            }
        });
    }
}

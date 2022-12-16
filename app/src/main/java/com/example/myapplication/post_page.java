package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.chat_room.Chat_Data;
import com.example.myapplication.chat_room.chat_room_activity;
import com.example.myapplication.post.post_adapter;
import com.example.myapplication.post.write_info;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import org.w3c.dom.Text;

public class post_page extends AppCompatActivity {
    Button chatbtn;
    TextView title, text, price,view_name, dong, view_post_time;
    String num, txt, s, dong_name, nick_name, other_name, other_dong, uri;
    ImageView post_picture;
    Button heart_button;

    private RecyclerView.Adapter adapter;
    private ArrayList<write_info> arrayList;
    private FirebaseFirestore db;

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
        post_picture = (ImageView) findViewById(R.id.post_picture);
        heart_button = (Button)findViewById(R.id.heart_button);

        db = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<write_info>();
        adapter = new post_adapter(arrayList, post_page.this);

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

        //사진
        if (secondIntent.hasExtra("uri")) {
            uri = secondIntent.getStringExtra("uri");
            Glide.with(post_page.this)
                    .load("https://firebasestorage.googleapis.com/v0/b/mobile-programming-978f9.appspot.com/o/posts%2F"+uri+".jpg?alt=media")
                    .into(post_picture);
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
                intent.putExtra("uri",uri);
                startActivity(intent);
            }
        });

        heart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heart_button.isSelected()==true){
                    heart_button.setSelected(false);
                }
                else{
                    heart_button.setSelected(true);
                }
            }
        });

    }
}

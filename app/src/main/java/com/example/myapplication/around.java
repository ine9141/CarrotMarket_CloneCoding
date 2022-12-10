package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class around extends AppCompatActivity {
    Button home, life, around, chat, my;
    TextView dong_name;
    String s,nick_name;
    ListView rec_list;
    RecyclerView coupon_list;
    listitemadapter rec_listadapter;
    cardviewadapter cou_adapter;
    ArrayList<cardviewitem> c_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.around);
        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //하단 액션바
        home = (Button) findViewById(R.id.home);
        life = (Button) findViewById(R.id.life);
        around = (Button) findViewById(R.id.around);
        chat = (Button) findViewById(R.id.chat);
        my = (Button) findViewById(R.id.my);
        dong_name = (TextView) findViewById(R.id.dong_name);

        //게시글 리스트
        rec_list = (ListView) findViewById(R.id.rec_list);
        coupon_list = (RecyclerView) findViewById(R.id.coupon_list) ;


        //추천가게 리스트
        rec_listadapter = new listitemadapter();
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "A가게", "좋았어요", "하루 전");
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "B가게", "맛없었어요", "이틀 전");
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "C가게", "사장님이 불친절해요", "사흘 전");

        rec_list.setAdapter(rec_listadapter);

        addItem(ContextCompat.getDrawable(
                this,R.drawable.chat),"A가게", "A요리를 하는 가게", "AAAAA");
        addItem(ContextCompat.getDrawable(
                this,R.drawable.chat),"A가게", "A요리를 하는 가게", "BBBB");
        addItem(ContextCompat.getDrawable(
                this,R.drawable.chat),"A가게", "A요리를 하는 가게", "CCCCC");
        addItem(ContextCompat.getDrawable(
                this,R.drawable.chat),"A가게", "A요리를 하는 가게", "DDDD");
        addItem(ContextCompat.getDrawable(
                this,R.drawable.chat),"A가게", "A요리를 하는 가게", "EEEEE");

        cou_adapter = new cardviewadapter(c_list);
        coupon_list.setAdapter(cou_adapter);
        coupon_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        cou_adapter.notifyDataSetChanged();


        //동네이름 설정
        Intent secondIntent = getIntent();
        if (secondIntent.hasExtra("dong_s")) {
            s = secondIntent.getStringExtra("dong_s");
            dong_name.setText(s);
        }

        //닉네임 설정
        if (secondIntent.hasExtra("nick_name")) {
            nick_name = secondIntent.getStringExtra("nick_name");
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
    public void addItem(Drawable img, String shop_name, String shop_content, String content) {
        cardviewitem item = new cardviewitem();

        item.setDrawable(img);
        item.setShop_name(shop_name);
        item.setShop_content(shop_content);
        item.setContent(content);

        c_list.add(item);
    }
}
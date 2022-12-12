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
    ListView rec_list, jop_list, near_list;
    RecyclerView coupon_list, shop_list, mini_list, carrot_list, product_list;
    listitemadapter rec_listadapter, jop_listadapter;
    cardviewadapter coupon_adapter, shop_adapter, mini_adapter, carrot_adapter, product_adapter;
    ArrayList<cardviewitem> c_list = new ArrayList<>();
    ArrayList<cardviewitem> s_list = new ArrayList<>();
    ArrayList<cardviewitem> m_list = new ArrayList<>();
    ArrayList<cardviewitem> cr_list = new ArrayList<>();
    ArrayList<cardviewitem> p_list = new ArrayList<>();
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
        jop_list = (ListView) findViewById(R.id.jop_list);


        coupon_list = (RecyclerView) findViewById(R.id.coupon_list) ;
        shop_list = (RecyclerView) findViewById(R.id.shop_list) ;
        mini_list = (RecyclerView) findViewById(R.id.mini_list) ;
        carrot_list = (RecyclerView) findViewById(R.id.carrot_list) ;
        product_list = (RecyclerView) findViewById(R.id.product_list) ;



       //어댑터 초기화
        rec_listadapter = new listitemadapter();
        jop_listadapter = new listitemadapter();

        //추천가게리스트
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "A가게", "좋았어요", "하루 전");
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "B가게", "가격 퀄리티 다 최고에요", "이틀 전");
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "C가게", "맛있어요", "사흘 전");

        //아르바이트 리스트
        jop_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "인부 모집", "일급 10만원", "하루 전");
        jop_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "헤어샵 직원구함", "월급 200만원", "이틀 전");
        jop_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.around), "홀서빙", "최저시급", "사흘 전");


        //리사이클러 뷰들 차례대로 아이템추가

        //쿠폰리스트
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"A가게", "세차 할인", "10퍼센트 할인");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"B가게", "커피 할인", "5퍼센트 할인");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"C가게", "엔진오일", "1만원 할인");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"D가게", "주유권", "10만원");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"E가게", "견적금액할인", "5프로 할인");

        //가게리스트
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"카페", "신선한 원두 사용", "향좋고 맛있습니다");
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"정육점", "정육점", "사장님이 친절해요");
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"꽃집", "꽃다발/꽃바구니/조화", "꽃들이 이쁩니다. 예약이 편해요");
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"베이커리", "당일생산, 당일판매", "방금 구워서 따뜻해요");

        //미니당근 리스트
        addItem(m_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"가게", "가게입니다", "장보러 종종 이용해요");
        addItem(m_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"청소업체", "청소업체입니다", "깨끗하게 청소해줘요");
        addItem(m_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"편의점", "편의점입니다", "출근 때 종종 이용합니다");

        //추천상품리스트
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"A 과일가게", "사과 3kg", "16,500원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"B 농장", "조생감귤", "13,000원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"C 생선가게", "생갈치", "19,900원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"D 농원", "샤인머스켓", "13,500원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"C 생선가게", "딱새우", "19,900원");

        //당근이야기 리스트
        addItem(cr_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"나의 당근 이야기01", "나의 차 이야기", "내용1");
        addItem(cr_list, ContextCompat.getDrawable(
                this,R.drawable.chat),"나의 당근이야기 02", "동네 떡집", "내용2");

        //어댑터 리스트뷰에 적용
        jop_list.setAdapter(jop_listadapter);
        rec_list.setAdapter(rec_listadapter);

        coupon_adapter = new cardviewadapter(c_list);
        coupon_list.setAdapter(coupon_adapter);
        coupon_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        coupon_adapter.notifyDataSetChanged();

        shop_adapter = new cardviewadapter(s_list);
        shop_list.setAdapter(shop_adapter);
        shop_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        shop_adapter.notifyDataSetChanged();

        mini_adapter = new cardviewadapter(m_list);
        mini_list.setAdapter(mini_adapter);
        mini_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        mini_adapter.notifyDataSetChanged();

        product_adapter = new cardviewadapter(p_list);
        product_list.setAdapter(product_adapter);
        product_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        product_adapter.notifyDataSetChanged();

        carrot_adapter = new cardviewadapter(cr_list);
        carrot_list.setAdapter(carrot_adapter);
        carrot_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        carrot_adapter.notifyDataSetChanged();


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
    public void addItem(ArrayList<cardviewitem> list, Drawable img, String shop_name, String shop_content, String content) {
        cardviewitem item = new cardviewitem();

        item.setDrawable(img);
        item.setShop_name(shop_name);
        item.setShop_content(shop_content);
        item.setContent(content);

        list.add(item);
    }
}
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
    ListView rec_list, job_list;
    RecyclerView coupon_list, shop_list, mini_list, carrot_list, product_list;
    list_item_adapter rec_listadapter;
    job_list_adapter job_listadapter;
    cardview_adapter shop_adapter, mini_adapter, carrot_adapter;
    coupon_adapter coupon_adapter;
    product_adapter product_adapter;
    ArrayList<cardview_item> c_list = new ArrayList<>();
    ArrayList<cardview_item> s_list = new ArrayList<>();
    ArrayList<cardview_item> m_list = new ArrayList<>();
    ArrayList<cardview_item> cr_list = new ArrayList<>();
    ArrayList<cardview_item> p_list = new ArrayList<>();

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
        job_list = (ListView) findViewById(R.id.job_list);


        coupon_list = (RecyclerView) findViewById(R.id.coupon_list) ;
        shop_list = (RecyclerView) findViewById(R.id.shop_list) ;
        mini_list = (RecyclerView) findViewById(R.id.mini_list) ;
        carrot_list = (RecyclerView) findViewById(R.id.carrot_list) ;
        product_list = (RecyclerView) findViewById(R.id.product_list) ;



       //어댑터 초기화
        rec_listadapter = new list_item_adapter();
        job_listadapter = new  job_list_adapter();

        //추천가게리스트
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.cafe), "카페", "좋았어요", "하루 전");
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.meat), "정육점", "신선해요", "이틀 전");
        rec_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.dak_galbi), "닭갈비", "맛있어요", "사흘 전");

        //아르바이트 리스트
        job_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.build), "건설", "인부모집", "일급 10만원");
        job_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.hair_shop), "헤어샵", "헤어 디자이너 모집", "일급 10만원");
        job_listadapter.additem(ContextCompat.getDrawable(
                this,R.drawable.gs25), "편의점", "오후알바 구합니다", "시급 10,000원");


        //리사이클러 뷰들 차례대로 아이템추가

        //쿠폰리스트
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.wash_car),"세차점", "세차 할인", "10퍼센트 할인");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.cafe),"카페", "커피 할인", "5퍼센트 할인");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.car_repair),"자동차수리점", "엔진오일", "1만원 할인");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.gas_station),"주유소", "주유권", "10만원");
        addItem(c_list, ContextCompat.getDrawable(
                this,R.drawable.interior),"인테리어", "견적금액할인", "5프로 할인");

        //가게리스트
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.cafe),"카페", "신선한 원두 사용", "향좋고 맛있습니다");
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.meat),"정육점", "정육점", "사장님이 친절해요");
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.flower_shop),"꽃집", "꽃다발/꽃바구니/조화", "꽃들이 이쁩니다. 예약이 편해요");
        addItem(s_list, ContextCompat.getDrawable(
                this,R.drawable.bakery),"베이커리", "당일생산, 당일판매", "방금 구워서 따뜻해요");

        //미니당근 리스트
        addItem(m_list, ContextCompat.getDrawable(
                this,R.drawable.meat),"정육점", "신선한 고기 팝니다", "고기가 신선해요");
        addItem(m_list, ContextCompat.getDrawable(
                this,R.drawable.cleaner),"청소업체", "청소업체입니다", "깨끗하게 청소해줘요");
        addItem(m_list, ContextCompat.getDrawable(
                this,R.drawable.gs25),"편의점", "편의점입니다", "출근 때 종종 이용합니다");

        //추천상품리스트
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.apple),"과일가게", "사과 3kg", "16,500원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.orange),"농장", "조생감귤", "13,000원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.galchi),"생선가게", "생갈치", "19,900원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.muscat),"농원", "샤인머스켓", "13,500원");
        addItem(p_list, ContextCompat.getDrawable(
                this,R.drawable.shirimp),"생선가게", "딱새우", "19,900원");

        //당근이야기 리스트
        addItem(cr_list, ContextCompat.getDrawable(
                this,R.drawable.avante),"나의 당근 이야기01", "나의 차 이야기", "아반뗴 차주 아무개씨");
        addItem(cr_list, ContextCompat.getDrawable(
                this,R.drawable.rice_cake),"나의 당근이야기 02", "동네 떡집", "창원 떡집");

        //어댑터 리스트뷰에 적용
        job_list.setAdapter(job_listadapter);
        rec_list.setAdapter(rec_listadapter);

        coupon_adapter = new coupon_adapter(c_list);
        coupon_list.setAdapter(coupon_adapter);
        coupon_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        coupon_adapter.notifyDataSetChanged();

        shop_adapter = new cardview_adapter(s_list);
        shop_list.setAdapter(shop_adapter);
        shop_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        shop_adapter.notifyDataSetChanged();

        mini_adapter = new cardview_adapter(m_list);
        mini_list.setAdapter(mini_adapter);
        mini_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        mini_adapter.notifyDataSetChanged();

        product_adapter = new product_adapter(p_list);
        product_list.setAdapter(product_adapter);
        product_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        product_adapter.notifyDataSetChanged();

        carrot_adapter = new cardview_adapter(cr_list);
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
    public void addItem(ArrayList<cardview_item> list, Drawable img, String shop_name, String shop_content, String content) {
        cardview_item item = new cardview_item();

        item.setDrawable(img);
        item.setShop_name(shop_name);
        item.setShop_content(shop_content);
        item.setContent(content);

        list.add(item);
    }
}
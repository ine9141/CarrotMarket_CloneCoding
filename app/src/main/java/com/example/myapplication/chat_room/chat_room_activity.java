package com.example.myapplication.chat_room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chat_room_activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Chat_Data> chatList;
    private  String myName = "user1";


    private EditText EditText_chat;
    private ImageButton btn_back, btn_call, btn_set,btn_send,btn_add;
    private DatabaseReference myRef;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        myName = intent.getStringExtra("myName");

        btn_send = (ImageButton) findViewById(R.id.Btn_send);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_call = (ImageButton) findViewById(R.id.btn_call);
        btn_set = (ImageButton) findViewById(R.id.btn_set_chat) ;
        EditText_chat = findViewById(R.id.Edit_msg);


        mRecyclerView = findViewById(R.id.chat_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new Chat_Adapter(chatList, chat_room_activity.this, myName);
        mRecyclerView.setAdapter(mAdapter);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = EditText_chat.getText().toString();
                String msg_time = getTime();

                if(msg != null){
                    Chat_Data chat = new Chat_Data();
                    chat.setName(myName);
                    chat.setMsg(msg);
                    chat.setTime(msg_time);
                    myRef.push().setValue(chat);
                }
                new Handler().postDelayed(new Runnable() {              //scrollToPosition 딜레이
                    @Override
                    public void run() {
                        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                    }
                }, 200);

            }

        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Chat_Data chat = snapshot.getValue(Chat_Data.class);
                ((Chat_Adapter)mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat msg_dateFormat = new SimpleDateFormat("hh:mm aa");
        String time  = msg_dateFormat.format(date);

        return time;
    }
}
package com.example.myapplication.chat_room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class chat_room_activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Chat_Data> chatList;
    private  String myName = "nick1";



    private EditText EditText_chat;
    private Button btn_send;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        myName = intent.getStringExtra("myName");

        btn_send =findViewById(R.id.Btn_send);
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
                if(msg != null){
                    Chat_Data chat = new Chat_Data();
                    chat.setName(myName);
                    chat.setMsg(msg);
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


        /*
        Chat_Data chat = new Chat_Data();
        chat.setName(nick);
        chat.setMsg("hi");
        myRef.setValue(chat);
        */

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



    }
}
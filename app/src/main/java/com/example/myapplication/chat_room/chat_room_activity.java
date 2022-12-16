package com.example.myapplication.chat_room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.chat;
import com.example.myapplication.chat_list_Adapter;
import com.example.myapplication.chat_list_data;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class chat_room_activity extends AppCompatActivity implements BottomSheet.BottomSheetListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Chat_Data> chatList;
    private List<chat_list_data> chatListData;
    private String myName = "user1";
    private String otherName;
    private String user_dong;
    private String url;
    private String chat_room_name;
    private TextView other_name_room;
    private EditText EditText_chat;
    private ImageButton btn_back, btn_call, btn_set, btn_send, btn_add;
    private DatabaseReference myRef;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        myName = intent.getStringExtra("myName");
        otherName = intent.getStringExtra("otherName");
        user_dong = intent.getStringExtra("user_dong");
        url = intent.getStringExtra("URL");
        if (myName == null || otherName == null) {
            Toast.makeText(getApplicationContext(), "아이디 필요", Toast.LENGTH_SHORT).show();
            finish();
        }
        //데이터 베이스에 유저 둘을 묶기 위함
        chat_room_name = myName.compareTo(otherName) < 0 ? myName + otherName : otherName + myName;
        btn_send = (ImageButton) findViewById(R.id.Btn_send);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_call = (ImageButton) findViewById(R.id.btn_call);
        btn_set = (ImageButton) findViewById(R.id.btn_set_chat);
        btn_add = (ImageButton) findViewById(R.id.Btn_add_chat);
        EditText_chat = findViewById(R.id.Edit_msg);
        other_name_room = (TextView) findViewById(R.id.other_name_room);
        other_name_room.setText(otherName);
        if (url != null) {
            EditText_chat.setText(url);
        }
        mRecyclerView = findViewById(R.id.chat_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        chatList = new ArrayList<>();
        mAdapter = new Chat_Adapter(chatList, chat_room_activity.this, myName);
        mRecyclerView.setAdapter(mAdapter);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01055555555"));
                startActivity(call_intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet bottomSheet = new BottomSheet();
                Bundle bundle = new Bundle();
                bundle.putString("myName", myName);
                bundle.putString("otherName", otherName);
                bundle.putString("room_name",chat_room_name);
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(), "bottom sheet");
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = EditText_chat.getText().toString();
                String msg_time = getTime();
                if (msg != null) {
                    Chat_Data chat = new Chat_Data();
                    chat.setName(myName);
                    chat.setMsg(msg);
                    chat.setTime(msg_time);
                    chat_list_data chatL= new chat_list_data();
                    chatL.setID_1(myName);
                    chatL.setID_2(otherName);
                    chatL.setLast_msg(msg);
                    chatL.setUser_dong(user_dong);
                    chatL.setLast_time(getNow());
                    myRef.child("chat").child(chat_room_name).child("chat_info").setValue(chatL);
                    myRef.child("chat").child(chat_room_name).child("chat_log").push().setValue(chat);
                }
                EditText_chat.setText("");
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("chat").child(chat_room_name).child("chat_log").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Chat_Data chat = snapshot.getValue(Chat_Data.class);
                ((Chat_Adapter) mAdapter).addChat(chat);
                new Handler().postDelayed(new Runnable() {              //scrollToPosition 딜레이
                    @Override
                    public void run() {
                        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    }
                }, 100);
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
        SimpleDateFormat msg_dateFormat = new SimpleDateFormat("hh:mm aa", Locale.KOREA);
        String time = msg_dateFormat.format(date);
        return time;
    }
    private long getNow(){
        long now = System.currentTimeMillis();
        return now;
    }
    @Override
    public void onButtonClicked() {
    }
}
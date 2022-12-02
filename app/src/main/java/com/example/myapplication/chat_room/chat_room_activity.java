package com.example.myapplication.chat_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Date;

public class chat_room_activity extends AppCompatActivity {
    private ArrayList<Chat_DataItem> dataList;
    private Chat_Adapter chat_adapter;
    private Button btn_send;
    private EditText edit_msg;
    private String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btn_send = (Button)findViewById(R.id.Btn_send);
        edit_msg = (EditText) findViewById(R.id.Edit_msg);
        RecyclerView recyclerView = findViewById(R.id.chat_recycler_view);


        dataList = new ArrayList<>();
        dataList.add(new Chat_DataItem("데모 2022년 12월 02일",null,chat_code.Chat_ViewType.CENTER_CONTENT));
        dataList.add(new Chat_DataItem("안녕하세요","상대방",chat_code.Chat_ViewType.LEFT_CONTENT));

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = edit_msg.getText().toString();
                dataList.add(new Chat_DataItem(msg,"ME",chat_code.Chat_ViewType.RIGHT_CONTENT));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chat_adapter = new Chat_Adapter(dataList);
                        recyclerView.scrollToPosition(chat_adapter.getItemCount()-1);
                    }
                }, 200);

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new Chat_Adapter(dataList));


    }
}
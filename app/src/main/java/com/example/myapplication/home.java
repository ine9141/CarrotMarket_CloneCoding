package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.post.post_adapter;
import com.example.myapplication.post.write_info;
import com.example.myapplication.post.write_post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    //파이어베이스
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<write_info> arrayList;
    private FirebaseFirestore db;
    ProgressDialog progressDialog;


    private static final String TAG = "home";
    Button home, life, around, chat, my;
    FloatingActionButton post_button;
    TextView dong_name;

    public static Context mContext;
    public String s, nick_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mContext = this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<write_info>();
        adapter = new post_adapter(arrayList, home.this);

        recyclerView.setAdapter(adapter);

        EventChangeListener();

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //글 올리기
        post_button = findViewById(R.id.postButton);

        //하단 액션바
        home = (Button) findViewById(R.id.home);
        life = (Button) findViewById(R.id.life);
        around = (Button) findViewById(R.id.around);
        chat = (Button) findViewById(R.id.chat);
        my = (Button) findViewById(R.id.my);
        dong_name = (TextView) findViewById(R.id.dong_name);


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

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), write_post.class);
                intent.putExtra("dong_s", s);
                intent.putExtra("nick_name", nick_name);
                startActivity(intent);
            }
        });
    }

    private void EventChangeListener() {
        db.collection("post").orderBy("createAt", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("error!!", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED &&
                                    (dc.getDocument().toObject(write_info.class).getDong() != null) &&
                                    (dc.getDocument().toObject(write_info.class).getDong().equals(s))) {
                                arrayList.add(dc.getDocument().toObject(write_info.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }
}
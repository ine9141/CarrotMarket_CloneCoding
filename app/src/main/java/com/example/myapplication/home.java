package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.post.post_adapter;
import com.example.myapplication.post.write_info;
import com.example.myapplication.post.write_post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class home extends AppCompatActivity {

    //파이어베이스
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<write_info> arrayList;
    private FirebaseFirestore db;


    private static final String TAG = "home";
    Button home, life, around, chat, my;
    FloatingActionButton post_button;
    TextView dong_name;
    String s, nick_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

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

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<write_info>();
        adapter = new post_adapter(arrayList,home.this);

        recyclerView.setAdapter(adapter);

        EventChangeListener();





}

    private void EventChangeListener() {
        db.collection("post")//.orderBy("createAt", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error!= null){
                            Log.e("error!!",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED){
                                arrayList.add(dc.getDocument().toObject(write_info.class) );
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    }



/*recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<write_info>(); //write객체 담을 어레이 리스트 - 어탭터로
        db = FirebaseFirestore.getInstance(); //파이어베이스 연동

        db.collection("post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        arrayList.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                arrayList.add(new write_info(
                                        document.getData().get("title").toString(),
                                        //.get("price").toString(),
                                        document.getData().get("contents").toString(),
                                        //new Date(document.getDate("createdAt").getTime()),
                                        document.getData().get("uri").toString()

                                ));

                            }
                            adapter = new post_adapter(arrayList, home.this);//어탭터에 리스트 보내기
                            recyclerView.setAdapter(adapter);//리사이클러뷰 어뎁터 연결

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }*/
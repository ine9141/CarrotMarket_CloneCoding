package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class write_post extends AppCompatActivity {
    private static final String TAG = "write_post";
    Button done_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_post);

        done_button = findViewById(R.id.done_button);


        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileUpdate();

            }
        });
    }

    private void profileUpdate(){
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        final String price = String.valueOf(findViewById(R.id.priceEditText));
        final String contents = ((EditText) findViewById(R.id.contentsEditText)).getText().toString();

        if(title.length()>0 && contents.length()>0){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            write_info writeInfo;
            assert user != null;
            writeInfo = new write_info(title,price,contents, user.getUid());
            uploader(writeInfo);
        }

        else{
            startToast("제목,카테고리,내용은 필수 입력 항목이에요.");
        }
    }

    private void uploader(write_info writeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Add a new document with a generated ID
        db.collection("post")
                .add(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });





    }

    private void startToast(String msg)
    { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();}



}
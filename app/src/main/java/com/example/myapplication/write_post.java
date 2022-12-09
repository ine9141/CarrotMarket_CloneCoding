package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class write_post extends AppCompatActivity {
    private static final String TAG = "write_post";
    private String path;
    Button done_button;
    ImageButton add_image_button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_post);

        done_button = (Button) findViewById(R.id.done_button);
        add_image_button = (ImageButton) findViewById(R.id.add_image_button);
        imageView = (ImageView)findViewById(R.id.imageView);





        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storageUpload();

            }
        });

        add_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);


            }
        });

    }

    private void storageUpload() {
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        final String price = String.valueOf(findViewById(R.id.priceEditText));
        final String contents = ((EditText) findViewById(R.id.contentsEditText)).getText().toString();

        if (title.length() > 0 && contents.length() > 0) {
           // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
//, user.getUid()
            write_info writeInfo;
            writeInfo = new write_info(title,price,contents);
            storeUpload(writeInfo);
        }

        else{
            startToast("제목,카테고리,내용은 필수 입력 항목이에요.");
        }
    }

    private void storeUpload(write_info writeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    imageView.setImageURI(uri);
                }
                break;
        }
    }

    public String getPath(Uri uri){
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);
    }


}







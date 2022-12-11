package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;


public class write_post extends AppCompatActivity {

    private FirebaseStorage storage;
    private static final String TAG = "write_post";
    private Uri uri;
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
        storage = FirebaseStorage.getInstance();

        //상단 액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    storageUpload();
                } catch (FileNotFoundException e) {
                    Toast.makeText(write_post.this, "실패",Toast.LENGTH_SHORT).show();
                }




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

    private void storageUpload() throws FileNotFoundException {
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        final Number price = Integer.parseInt(((EditText)findViewById(R.id.priceEditText)).getText().toString());
        final String contents = ((EditText) findViewById(R.id.contentsEditText)).getText().toString();


        if (title.length() > 0 && contents.length() > 0 && price!=null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = db.collection("post").document();

            if(uri != null){
                StorageReference storageRef = storage.getReference();
                StorageReference mountainsRef = storageRef.child("posts/"+documentReference.getId()+".jpg");
                UploadTask uploadTask = mountainsRef.putFile(uri);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(write_post.this,"사진 업로드 실패",Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(write_post.this,"사진 업로드 완료",Toast.LENGTH_SHORT).show();

                        mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.e("로그",uri.toString());
                            }
                        });
                    }
                });
            }




            write_info writeInfo;
            writeInfo = new write_info(title,price,contents,new Date(),uri.toString()/*,user.getUid()*/);
            storeUpload(documentReference,writeInfo);
        }

        else{
            startToast("제목,카테고리,내용은 필수 입력 항목이에요.");
        }
    }

    private void storeUpload(DocumentReference documentReference,write_info writeInfo){
        documentReference.set(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        /*FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                });  날리기 */

    }

    private void startToast(String msg)
    { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();}



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                   imageView.setImageURI(uri);
                }
                break;
        }
    }





}







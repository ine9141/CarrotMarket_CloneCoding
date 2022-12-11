package com.example.myapplication.chat_room;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class BottomSheet extends BottomSheetDialogFragment {

    private View view;
    private BottomSheetListener mListener;

    private ImageView imgv;

    private ImageButton chat_add_location;
    private ImageButton chat_add_img;
    private Uri imgUri;
    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private final StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.chat_bottom_sheet, container, false);
        mListener = (BottomSheetListener) getContext();

        chat_add_location = view.findViewById(R.id.Btn_chat_location);
        chat_add_img = view.findViewById(R.id.Btn_chat_img);

        imgv = view.findViewById(R.id.sample_img);


        chat_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                activityResult.launch(intent);
                if(imgUri != null)
                    uploadToFirebase(imgUri);
                else
                    Toast.makeText(getContext(),"취소",Toast.LENGTH_SHORT).show();


            }
        });




        chat_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), location_share.class);
                intent.putExtra("myName",getArguments().getString("myName"));
                intent.putExtra("otherName",getArguments().getString("otherName"));
                startActivity(intent);
            }
        });



        return view;

    } //oncreateView
    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        imgUri = result.getData().getData();


                        uploadToFirebase(imgUri);

                        imgv.setImageURI(imgUri);
                        imgv.setVisibility(View.VISIBLE);
                    }
                }
            }
    );
    private void uploadToFirebase(Uri uri){
        StorageReference fileRef = reference.child("chat_img/").child(getArguments().getString("myName")+"."+System.currentTimeMillis()+'.'+"img");
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        chat_img img = new chat_img(uri.toString());
                        String imgId = root.push().getKey();
                        root.child(imgId).setValue(img);

                    }
                });
            }
        });
    }






    public interface BottomSheetListener{
        void onButtonClicked();
    }
}



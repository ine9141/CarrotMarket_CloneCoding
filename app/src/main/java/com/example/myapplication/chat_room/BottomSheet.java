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

import com.example.myapplication.chat_list_data;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BottomSheet extends BottomSheetDialogFragment {

    private View view;
    private BottomSheetListener mListener;

    private ImageView imgv;

    private ImageButton chat_add_location;
    private ImageButton chat_add_img;
    private Uri imgUri;


    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference("chat");
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
                        String name = getArguments().getString("myName");
                        String name_other = getArguments().getString("otherName");
                        String room_name = getArguments().getString("room_name");
                        String time = getTime();
                        Chat_Data chat = new Chat_Data();
                        chat.setMsg(uri.toString());
                        chat.setName(name);
                        chat.setTime(time);

                        chat_list_data chatL= new chat_list_data();
                        chatL.setID_1(name);
                        chatL.setID_2(name_other);
                        chatL.setLast_msg(name+"님이 사진을 보냈습니다.");

                        root.child(room_name).child("chat_info").setValue(chatL);
                        root.child(room_name).child("chat_log").push().setValue(chat);


                    }
                });
            }
        });
    }


    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat msg_dateFormat = new SimpleDateFormat("hh:mm aa");
        String time = msg_dateFormat.format(date);

        return time;
    }



    public interface BottomSheetListener{
        void onButtonClicked();
    }
}



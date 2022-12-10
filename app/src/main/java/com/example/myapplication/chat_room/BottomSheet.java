package com.example.myapplication.chat_room;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {

    private View view;
    private BottomSheetListener mListener;

    private ImageButton chat_add_location;
    private ImageButton chat_add_img;
    private Uri imgUri;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.chat_bottom_sheet, container, false);
        mListener = (BottomSheetListener) getContext();

        chat_add_location = view.findViewById(R.id.Btn_chat_location);
        chat_add_img = view.findViewById(R.id.Btn_chat_img);

        chat_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,10);


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

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode == RESULT_OK){
                    imgUri = data.getData();
                    Toast.makeText(getContext(), imgUri.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public interface BottomSheetListener{
        void onButtonClicked();
    }
}



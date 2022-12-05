package com.example.myapplication.chat_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {

    private View view;
    private BottomSheetListener mListener;

    private ImageButton chat_add_location;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.chat_bottom_sheet, container, false);
        mListener = (BottomSheetListener) getContext();

        chat_add_location = view.findViewById(R.id.Btn_chat_location);

        chat_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), location_share.class);
                startActivity(intent);

            }
        });



        return view;

    }
    public interface BottomSheetListener{
        void onButtonClicked();
    }
}



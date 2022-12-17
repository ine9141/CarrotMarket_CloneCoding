package com.example.myapplication.chat_room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.MyViewHolder> {
    private List<Chat_Data> mDataset;
    private String myNickname;
    private static Context context;

    public Chat_Adapter(List<Chat_Data> myDataset, Context context, String myNickName){
        mDataset = myDataset;
        this.myNickname =myNickName;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public TextView TextView_time_left, TextView_time_right;
        public ImageView chat_imgv;
        public View rootView;

        public CircleImageView Cir_img;
        public LinearLayout chat_linear;

        public MyViewHolder(View v){
            super(v);
            TextView_nickname = v.findViewById(R.id.tv_name);
            TextView_msg = v.findViewById(R.id.tv_msg);

            TextView_time_left = v.findViewById(R.id.tv_time_left);
            TextView_time_right = v.findViewById(R.id.tv_time_right);
            chat_imgv = v.findViewById(R.id.chat_img);
            rootView = v;

            Cir_img = v.findViewById(R.id.imgv);
            chat_linear = v.findViewById(R.id.Chat_linear);


            v.setClickable(true);
            v.setEnabled(true);


            //장소공유 채팅 클릭 이벤트 입니다 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextView_msg.getText().toString().contains("google.co.kr/maps/")==true){

                        String s = TextView_msg.getText().toString();
                        String[] token = s.split("@");
                        String[] token2 = token[1].split(",");

                        Intent intent = new Intent(context, location_join.class);
                        intent.putExtra("x",token2[0]);
                        intent.putExtra("y",token2[1]);
                        context.startActivity(intent);

                    }
                }
            });

        }
    }


    public Chat_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){

       LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.chat_room_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Chat_Data chat = mDataset.get(position);
        holder.TextView_nickname.setText(chat.getName());
        holder.TextView_msg.setText(chat.getMsg());
        holder.TextView_time_left.setText(chat.getTime());
        holder.TextView_time_right.setText(chat.getTime());
        if(chat.getName().equals(this.myNickname)){
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.TextView_msg.setBackgroundResource(R.drawable.round_send_msg);
            holder.TextView_msg.setTextColor(Color.WHITE);
            holder.chat_linear.setGravity(Gravity.RIGHT);
            holder.TextView_nickname.setVisibility(View.INVISIBLE);
            holder.Cir_img.setVisibility(View.INVISIBLE);
            holder.TextView_time_right.setVisibility(View.INVISIBLE);
            holder.TextView_time_right.setMaxWidth(0);
            holder.TextView_time_right.setMaxHeight(0);
            if(chat.getMsg().contains("//firebasestorage")){
                Glide.with(context).load(chat.getMsg()).override(600,600).into(holder.chat_imgv);
                holder.chat_imgv.setClipToOutline(true);
                holder.chat_imgv.setVisibility(View.VISIBLE);
                holder.chat_imgv.setMaxHeight(150);
                holder.chat_imgv.setMaxWidth(150);
                holder.TextView_msg.setHeight(0);
                holder.TextView_msg.setWidth(0);
            }
        }
        else{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,0);
            holder.chat_linear.setLayoutParams(params);
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.TextView_time_left.setVisibility(View.INVISIBLE);
            holder.TextView_time_left.setMaxWidth(0);
            holder.TextView_time_left.setMaxHeight(0);
            if(chat.getMsg().contains("//firebasestorage")){
                Glide.with(context).load(chat.getMsg()).override(600,600).into(holder.chat_imgv);
                holder.chat_imgv.setVisibility(View.VISIBLE);
                holder.TextView_msg.setHeight(0);
                holder.TextView_msg.setWidth(0);
            }
        }
    }

    @Override
    public int getItemCount(){
        return mDataset == null ? 0 : mDataset.size();
    }
    public Chat_Data getChat(int position){
        return mDataset != null ? mDataset.get(position) : null ;
    }
    public void addChat(Chat_Data chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1);
    }





    @Override
    public int getItemViewType(int position){           //오버라이딩 하지 않으면 view 꼬임
        return position;
    }

}





package com.example.myapplication.chat_room;

import android.content.Context;
import android.graphics.Color;
import android.view.ContentInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.MyViewHolder> {
    private List<Chat_Data> mDataset;
    private String myNickname;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public View rootView;

        public CircleImageView Cir_img;
        public LinearLayout chat_linear;

        public MyViewHolder(View v){
            super(v);
            TextView_nickname = v.findViewById(R.id.tv_name);
            TextView_msg = v.findViewById(R.id.tv_msg);
            rootView = v;

            Cir_img = v.findViewById(R.id.imgv);
            chat_linear = v.findViewById(R.id.Chat_linear);

            v.setClickable(true);
            v.setEnabled(true);
        }
    }
    public Chat_Adapter(List<Chat_Data> myDataset, Context context, String myNickName){
        mDataset = myDataset;
        this.myNickname =myNickName;
    }

    public Chat_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){

       LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.chat_room_left_item_list, parent, false);



        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Chat_Data chat = mDataset.get(position);

        holder.TextView_nickname.setText(chat.getName());
        holder.TextView_msg.setText(chat.getMsg());

        if(chat.getName().equals(this.myNickname)){
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.TextView_msg.setBackgroundResource(R.drawable.round_send_msg);
            holder.TextView_msg.setTextColor(Color.WHITE);
            holder.chat_linear.setGravity(Gravity.RIGHT);

            holder.TextView_nickname.setVisibility(View.INVISIBLE);
            holder.Cir_img.setVisibility(View.INVISIBLE);


        }
        else{
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
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


    public class LeftViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgv;
        TextView tv_name;
        TextView tv_msg;
        TextView tv_time;
        public LeftViewHolder(@NonNull View itemView){
            super(itemView);
            imgv =(CircleImageView) itemView.findViewById(R.id.imgv);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }



}





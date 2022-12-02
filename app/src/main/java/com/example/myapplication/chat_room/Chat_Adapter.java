package com.example.myapplication.chat_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.MyViewHolder> {
    private List<Chat_Data> mDataset;
    private String myNickname;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public View rootView;

        public MyViewHolder(View v){
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            rootView = v;

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
                .inflate(R.layout.row_chat, parent, false);

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
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
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


}





package com.example.myapplication.chat_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static ArrayList<Chat_DataItem> chat_DataList = null;

    public Chat_Adapter(ArrayList<Chat_DataItem> dataList) { chat_DataList = dataList;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == chat_code.Chat_ViewType.CENTER_CONTENT){
            view = inflater.inflate(R.layout.chat_room_center_item_list, parent, false);
            return new CenterViewHolder(view);
        }else if(viewType == chat_code.Chat_ViewType.LEFT_CONTENT){
            view = inflater.inflate(R.layout.chat_room_left_item_list, parent, false);
            return new LeftViewHolder(view);
        }else{
            view = inflater.inflate(R.layout.chat_room_right_item_list,parent,false);
            return new RightViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof CenterViewHolder){
            ((CenterViewHolder)viewHolder).tv.setText(chat_DataList.get(position).chat_getContent());
        }else if(viewHolder instanceof LeftViewHolder){
            ((LeftViewHolder)viewHolder).tv_name.setText(chat_DataList.get(position).chat_getName());
            ((LeftViewHolder)viewHolder).tv_msg.setText(chat_DataList.get(position).chat_getContent());
        }else{
            ((RightViewHolder)viewHolder).tv_msg.setText(chat_DataList.get(position).chat_getContent());
        }
    }


    public int getItemCount(){
        return chat_DataList.size();
    }

    public int getItemViewType(int position){ return chat_DataList.get(position).chat_getViewType(); }

    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public CenterViewHolder(@NonNull View itemView){
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_chat);
        }

    }
    public class LeftViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgv;
        TextView tv_name;
        TextView tv_msg;
        TextView tv_time;

        public LeftViewHolder(@NonNull View itemView){
            super(itemView);
            imgv = (CircleImageView) itemView.findViewById(R.id.imgv);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);

        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView tv_msg;
        TextView tv_time;

        public RightViewHolder(@NonNull View itemView){
            super(itemView);
            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
        }
    }

}

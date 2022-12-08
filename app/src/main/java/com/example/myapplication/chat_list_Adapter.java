package com.example.myapplication;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.chat_room.Chat_Data;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_list_Adapter extends RecyclerView.Adapter<chat_list_Adapter.ListViewHolder> {

    public List<chat_list_data> lDataset;





    public static class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView chat_list_id;
        public TextView chat_last_msg;
        public View list_rootView;

        public CircleImageView circle_imgv;

        public ListViewHolder(View v){
            super(v);
            chat_list_id = v.findViewById(R.id.chat_list_id);
            chat_last_msg = v.findViewById(R.id.chat_last_msg);
            list_rootView = v;
            circle_imgv = v.findViewById(R.id.circle_imgv);
            v.setClickable(true);
            v.setEnabled(true);

        }
    }
    public chat_list_Adapter(List<chat_list_data> myDataset, Context context){
        lDataset = myDataset;
    }

    @NonNull
    @Override
    public chat_list_Adapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list,parent,false);
        ListViewHolder lh = new ListViewHolder(v);
        return lh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        chat_list_data chatD = lDataset.get(position);

        holder.chat_list_id.setText(chatD.getChat_id());
        holder.chat_last_msg.setText(chatD.getLast_msg());

    }

    @Override
    public int getItemCount() {
        return lDataset == null ? 0 : lDataset.size();
    }
    public chat_list_data getChat(int position){
        return lDataset != null ? lDataset.get(position) : null;
    }
    public void addChatList(chat_list_data chat){
        lDataset.add(chat);
        notifyDataSetChanged();
        //notifyItemInserted(lDataset.size()-1);
    }
    public void setChatList(chat_list_data chat){
        lDataset.set(0,chat);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){           //오버라이딩 하지 않으면 view 꼬임
        return position;
    }
}

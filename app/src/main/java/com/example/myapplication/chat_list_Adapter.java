package com.example.myapplication;
import static android.content.Intent.getIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.chat_room.Chat_Data;
import com.example.myapplication.chat_room.chat_list_img;
import com.example.myapplication.post.write_info;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class chat_list_Adapter extends RecyclerView.Adapter<chat_list_Adapter.ListViewHolder> {
    public List<chat_list_data> lDataset;
    public List<chat_list_img> IDataset;
    private String myNickName;
    public static class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView chat_list_id_1;
        public TextView chat_list_id_2;
        public TextView chat_last_msg;
        public TextView chat_last_time;
        public View list_rootView;
        public ImageView list_imgv;
        public CircleImageView circle_imgv;


        public ListViewHolder(View v){
            super(v);
            chat_list_id_1 = v.findViewById(R.id.chat_list_id_1);
            chat_list_id_2 = v.findViewById(R.id.chat_list_id_2);
            chat_last_msg = v.findViewById(R.id.chat_last_msg);
            chat_last_time = v.findViewById(R.id.list_time);
            list_rootView = v;
            circle_imgv = v.findViewById(R.id.circle_imgv);
            list_imgv = v.findViewById(R.id.list_imgv);
            v.setClickable(true);
            v.setEnabled(true);

        }
    }
    public chat_list_Adapter(List<chat_list_data> myDataset, Context context, String myNickName, List<chat_list_img> IIDataset){
        lDataset = myDataset;
        IDataset = IIDataset;
        this.myNickName = myNickName;
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

        for(int i=0; i<IDataset.size(); i++){
            if(IDataset.get(i).getRoom_name().contains(chatD.getID_1())&&
                IDataset.get(i).getRoom_name().contains(chatD.getID_2())) {
                String uri = IDataset.get(i).getImg_uri();
                Glide.with(holder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/mobile-programming-978f9.appspot.com/o/posts%2F"+uri+".jpg?alt=media")
                        .override(60,60)
                        .into(holder.list_imgv);
                break;
            }
        }

        if(myNickName.equals(chatD.getID_1())){
            holder.chat_list_id_2.setText(chatD.getID_2());
        }
        else{
            holder.chat_list_id_2.setText(chatD.getID_1());
        }
        holder.chat_last_msg.setText(chatD.getLast_msg());
        holder.chat_last_time.setText(getLastTime(chatD.getLast_time()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), com.example.myapplication.chat_room.chat_room_activity.class);
                intent.putExtra("myName",myNickName);
                intent.putExtra("otherName",holder.chat_list_id_2.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return lDataset == null ? 0 : lDataset.size();
    }
    public chat_list_data getChat(int position){
        return lDataset != null ? lDataset.get(position) : null;
    }
    public void addChatList(chat_list_data chat, chat_list_img chatI){
        if(chatI.getRoom_name()!=null) {
            IDataset.add(chatI);
        }
        if(chat.getID_1() == null) return;
        if(chat.getID_1().equals(myNickName) || chat.getID_2().equals(myNickName)) {
            lDataset.add(0,chat);
            notifyDataSetChanged();
        }
    }
    public void setChatList(chat_list_data chat){
        if(chat.getID_1() == null) return;
        if(chat.getID_1().equals(myNickName) || chat.getID_2().equals(myNickName)) {
            int i;
            for (i = 0; i < lDataset.size() - 1; i++) {
                if (chat.getID_2().equals(lDataset.get(i).getID_2()))
                    break;
            }
            lDataset.set(i, chat);
            notifyDataSetChanged();
        }

    }
    public void sortList(){
        Collections.sort(lDataset,cmpAsc);
    }
    Comparator<chat_list_data> cmpAsc = new Comparator<chat_list_data>() {
        @Override
        public int compare(chat_list_data s1, chat_list_data s2) {
            if (s1.getLast_time() > s2.getLast_time())
                return -1;
            else
                return 1;
        }
    };

    @Override
    public int getItemViewType(int position){           //오버라이딩 하지 않으면 view 꼬임
        return position;
    }
    private String getLastTime(long regTime){
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg = null;
        if (diffTime < 60) {
            msg = "방금 전";
        } else if ((diffTime /= 60) < 60) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= 60) < 24) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= 24) < 30) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= 30) < 12) {
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }
}
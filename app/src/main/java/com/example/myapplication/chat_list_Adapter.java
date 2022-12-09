package com.example.myapplication;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.chat_room.Chat_Data;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_list_Adapter extends RecyclerView.Adapter<chat_list_Adapter.ListViewHolder> {

    public List<chat_list_data> lDataset;
    private String myNickName;

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView chat_list_id_1;
        public TextView chat_list_id_2;
        public TextView chat_last_msg;
        public View list_rootView;

        public CircleImageView circle_imgv;

        public ListViewHolder(View v){
            super(v);
            chat_list_id_1 = v.findViewById(R.id.chat_list_id_1);
            chat_list_id_2 = v.findViewById(R.id.chat_list_id_2);
            chat_last_msg = v.findViewById(R.id.chat_last_msg);
            list_rootView = v;
            circle_imgv = v.findViewById(R.id.circle_imgv);
            v.setClickable(true);
            v.setEnabled(true);


        }
    }
    public chat_list_Adapter(List<chat_list_data> myDataset, Context context, String myNickName){
        lDataset = myDataset;
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

        if(myNickName.equals(chatD.getID_1())){
            holder.chat_list_id_2.setText(chatD.getID_2());
        }
        else{
            holder.chat_list_id_2.setText(chatD.getID_1());
        }

        holder.chat_last_msg.setText(chatD.getLast_msg());

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
    public void addChatList(chat_list_data chat){
        if(chat.getID_1() == null) return;
        lDataset.add(chat);
        notifyDataSetChanged();
        //notifyItemInserted(lDataset.size()-1);
    }
    public void setChatList(chat_list_data chat){
        if(chat.getID_1() == null) return;
        int i;
        for(i=0; i<lDataset.size()-1;i++){
            if(chat.getID_2().equals(lDataset.get(i).getID_2()))
                break;
        }
        lDataset.set(i,chat);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){           //오버라이딩 하지 않으면 view 꼬임
        return position;
    }
}


package com.example.myapplication.post;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.chat_room.location_join;
import com.example.myapplication.check_address;
import com.example.myapplication.home;
import com.example.myapplication.post_page;

import java.util.ArrayList;



public class post_adapter extends RecyclerView.Adapter<post_adapter.postViewHolder> {

    private ArrayList<write_info> arrayList;
    private static Context context;
    static write_info writeInfo;

    public post_adapter(ArrayList<write_info> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public post_adapter.postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new postViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull post_adapter.postViewHolder holder, @SuppressLint("RecyclerView") int position) {
        writeInfo = arrayList.get(position);

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getUri()) //glide로 사진 로드
                .into(holder.goods_img);

        holder.titleTextView.setText(writeInfo.getTitle());
        holder.priceTextView.setText(String.valueOf(writeInfo.getPrice()));
        holder.localTextView.setText(writeInfo.getDong());


        holder.post_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mPosition = holder.getAdapterPosition();
                Context context = v.getContext();
                Intent post_page = new Intent(context, com.example.myapplication.post_page.class);
                post_page.putExtra("price",arrayList.get(position).getPrice().toString());
                post_page.putExtra("title",arrayList.get(position).getTitle());
                post_page.putExtra("text",arrayList.get(position).getContents());
                post_page.putExtra("other",arrayList.get(position).getPublisher());
                post_page.putExtra("dong",arrayList.get(position).getDong());
                post_page.putExtra("dong_s",((home)home.mContext).s);
                post_page.putExtra("nick_name",((home)home.mContext).nick_name);
                ((home) context).startActivity(post_page);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    //아이템 클릭 리스너 인터페이스
    interface OnItemClickListener{
        void onItemClick(View v, int position); //뷰와 포지션값
    }
    //리스너 객체 참조 변수
    private OnItemClickListener mListener = null;

    //리스너 객체 참조를 어댑터에 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public static class postViewHolder extends RecyclerView.ViewHolder {
        ImageView goods_img;
        TextView titleTextView;
        TextView priceTextView;
        TextView localTextView;
        LinearLayout post_layout;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);

            this.post_layout = itemView.findViewById(R.id.post_layout);
            this.goods_img = itemView.findViewById(R.id.imageView);
            this.titleTextView = itemView.findViewById(R.id.titleTextView);
            this.priceTextView = itemView.findViewById(R.id.priceTextView);
            this.localTextView = itemView.findViewById(R.id.localTextView);
        }
    }
}
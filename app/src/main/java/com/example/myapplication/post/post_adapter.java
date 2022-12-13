
package com.example.myapplication.post;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;



public class post_adapter extends RecyclerView.Adapter<post_adapter.postViewHolder> {


    private ArrayList<write_info> arrayList;
    private Context context;


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
    public void onBindViewHolder(@NonNull post_adapter.postViewHolder holder, int position) {
        write_info writeInfo = arrayList.get(position);

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getUri()) //glide로 사진 로드
                .into(holder.goods_img);

        holder.titleTextView.setText(writeInfo.getTitle());
        holder.priceTextView.setText(String.valueOf(writeInfo.getPrice()));
        


    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    public static class postViewHolder extends RecyclerView.ViewHolder {
        ImageView goods_img;
        TextView titleTextView;
        //TextView localTextVIew;
        TextView priceTextView;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            this.goods_img = itemView.findViewById(R.id.imageView);
            this.titleTextView = itemView.findViewById(R.id.titleTextView);
            this.priceTextView = itemView.findViewById(R.id.priceTextView);


        }
    }
}

























/*{

    private ArrayList<write_info> mDataset;
    private Activity activity;

    public post_adapter(Activity activity, ArrayList<write_info> myDataset) {
        this.mDataset = myDataset;
        this.activity = activity;
    }


    @NonNull
    @Override
    public post_adapter.postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.item_post,parent,false);
        //postViewHolder holder = new postViewHolder(view);
        return new postViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final postViewHolder holder, int position) {

        write_info writeInfo =mDataset.get(position);
        holder.titleTextView.setText(mDataset.get(position).getTitle());
        Log.e("로그: ","데이터: "+mDataset.get(position).getTitle());
       // holder.localTextView.setText(writeInfo.getlocal());
        holder.priceTextView.setText(String.valueOf(mDataset.get(position).getPrice()));



    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class postViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,localTextView,priceTextView;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            //localTextView = itemView.findViewById(R.id.localTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }
}
*/
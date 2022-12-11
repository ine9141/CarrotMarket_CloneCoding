
package com.example.myapplication;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class post_adapter extends RecyclerView.Adapter<post_adapter.postViewHolder>{

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

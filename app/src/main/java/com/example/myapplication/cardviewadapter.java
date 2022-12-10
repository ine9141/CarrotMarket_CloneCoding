package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cardviewadapter extends RecyclerView.Adapter<cardviewadapter.CustomViewHolder> {
    private ArrayList<cardviewitem> items = new ArrayList<cardviewitem>();
    private Context context;
    private int i;

    public cardviewadapter(ArrayList<cardviewitem> item){
        items = item;
    }
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_cardview, parent, false);
        cardviewadapter.CustomViewHolder holder = new cardviewadapter.CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        cardviewitem item = items.get(position);

        holder.img.setImageDrawable(item.getDrawable());
        holder.shop_name.setText(item.getShop_name());
        holder.shop_content.setText(item.getShop_content());
        holder.content.setText(item.getContent());

    }

    public int getItemCount() {
        return items.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView shop_name, shop_content, content;

        CustomViewHolder(View view) {
            super(view);

            img = view.findViewById(R.id.img);
            shop_name = view.findViewById(R.id.shop_name);
            shop_content = view.findViewById(R.id.shop_content);
            content = view.findViewById(R.id.content);
        }

    }

}

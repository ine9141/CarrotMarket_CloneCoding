package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cardview_adapter extends RecyclerView.Adapter<cardview_adapter.CustomViewHolder> {
    private ArrayList<cardview_item> items = new ArrayList<cardview_item>();
    private Context context;
    private int i;

    public cardview_adapter(ArrayList<cardview_item> item){
        items = item;
    }
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_cardview, parent, false);
        cardview_adapter.CustomViewHolder holder = new cardview_adapter.CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        cardview_item item = items.get(position);

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

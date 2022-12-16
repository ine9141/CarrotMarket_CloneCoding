package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class job_list_adapter extends BaseAdapter {
    private ArrayList<job_list_item> items = new ArrayList<job_list_item>();

    public job_list_adapter() {

    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.job_list_item, viewGroup, false);
        }
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView local = (TextView) view.findViewById(R.id.local);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView money = (TextView) view.findViewById(R.id.money);

        job_list_item list = items.get(i);

        img.setImageDrawable(list.getDrawable());
        local.setText(list.getLocal());
        content.setText(list.getContent());
        money.setText(list.getMoney());

        return view;
    }
    public void additem(Drawable drawable, String local, String content, String money) {
        job_list_item list = new job_list_item();
        list.setDrawable(drawable);
        list.setLocal(local);
        list.setContent(content);
        list.setMoney(money);

        items.add(list);
    }
}

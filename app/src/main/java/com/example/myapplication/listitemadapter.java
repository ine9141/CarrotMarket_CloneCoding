package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class listitemadapter extends BaseAdapter {
    private ArrayList<listitem> items = new ArrayList<listitem>();

    public listitemadapter() {

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
            view = inflater.inflate(R.layout.custom_list_item, viewGroup, false);
        }
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView time = (TextView) view.findViewById(R.id.time);

        listitem list = items.get(i);

        img.setImageDrawable(list.getDrawable());
        title.setText(list.getTitle());
        content.setText(list.getContent());
        time.setText(list.getTime());

        return view;
    }
    public void additem(Drawable drawable, String title, String content, String time) {
        listitem list = new listitem();
        list.setDrawable(drawable);
        list.setTitle(title);
        list.setContent(content);
        list.setTime(time);

        items.add(list);
    }
}

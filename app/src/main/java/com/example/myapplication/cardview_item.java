package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class cardview_item {
    private Drawable drawable;
    private String shop_name;
    private String shop_content;
    private String content;

    public cardview_item() {
    }

    public cardview_item(Drawable drawable, String shop_name, String shop_content, String content) {
        this.drawable = drawable;
        this.shop_name = shop_name;
        this.shop_content = shop_content;
        this.content = content;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_content() {
        return shop_content;
    }

    public void setShop_content(String shop_content) {
        this.shop_content = shop_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class listitem {
    private Drawable drawable;
    private String title;
    private String content;
    private String time;

    public listitem(Drawable drawable, String title, String content, String time) {
        this.drawable = drawable;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public listitem() {
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

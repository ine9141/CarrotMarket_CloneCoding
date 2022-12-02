package com.example.myapplication.chat_room;

public class Chat_DataItem {
    private String content;
    private String name;
    private int viewType;

    public Chat_DataItem(String content, String name, int viewType){
        this.content = content;
        this.viewType = viewType;
        this.name = name;
    }

    public String chat_getContent() {return content;}
    public String chat_getName(){return name;}
    public int chat_getViewType(){ return viewType;}

}

package com.example.myapplication.chat_room;

public class chat_img {
    private String imgUrl;
    private String name;
    private String time;
    chat_img(){

    }
    public chat_img(String imgUrl,String name,String time){
        this.imgUrl = imgUrl;
        this.name = name;
        this.time = time;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }

}

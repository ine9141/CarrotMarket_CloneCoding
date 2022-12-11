package com.example.myapplication.chat_room;

public class chat_img {
    private String imgUrl;

    chat_img(){

    }
    public chat_img(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
}

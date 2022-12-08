package com.example.myapplication;

public class chat_list_data {
    private String chat_id;
    private String last_msg;
    public String getChat_id(){
        return chat_id;
    }
    public void setChat_id(String chat_id){
        this.chat_id = chat_id;
    }
    public String getLast_msg(){
        return last_msg;
    }
    public void setLast_msg(String msg){
        this.last_msg = msg;
    }

}

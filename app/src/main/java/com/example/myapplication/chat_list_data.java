package com.example.myapplication;

public class chat_list_data {
    private String id_1;
    private String id_2;
    private String last_msg;
    public String getID_1(){
        return id_1;
    }
    public void setID_1(String id_1){
        this.id_1 = id_1;
    }
    public String getID_2() { return id_2; }
    public void setID_2(String id_2) { this.id_2 = id_2; }
    public String getLast_msg(){
        return last_msg;
    }
    public void setLast_msg(String msg){
        this.last_msg = msg;
    }

}

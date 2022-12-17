package com.example.myapplication;

public class chat_list_data {
    private String id_1;
    private String id_2;
    private String last_msg;
    private String user_dong;
    private long last_time;




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
    public String getUser_dong() { return user_dong; }
    public void setUser_dong(String user_dong) { this.user_dong = user_dong; }
    public void setLast_time(long last_time){this.last_time = last_time; }
    public long getLast_time(){ return last_time; }




}

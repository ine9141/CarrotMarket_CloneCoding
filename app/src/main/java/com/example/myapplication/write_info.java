package com.example.myapplication;

public class write_info {
    private String title;
    private String price;
    private String contents;
    private String publisher;


    public write_info(String title, String price, String contents,String publisher){
        this.title=title;
        this.price=price;
        this.contents=contents;
        this.publisher=publisher;
    }

    public String getTitle() { return this.title;}
    public void setTitle(String title) {this.title=title;}
    public String getPrice() { return this.price;}
    public void setPrice(String price) {this.price=price;}
    public String getContents() { return this.contents;}
    public void setContents(String contents) {this.contents=contents;}
    public String getPublisher() { return this.publisher;}
    public void setPublisher(String publisher) {this.publisher=publisher;}


}

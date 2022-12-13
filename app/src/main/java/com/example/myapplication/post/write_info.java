package com.example.myapplication.post;

import java.util.Date;
import java.util.zip.DataFormatException;

public class write_info {
    private String title;
    int price;
    private String contents;
    private Date createAt;
    private String uri;
    private String publisher;
    private String dong;

    public write_info() {
    }

    public write_info(String title, int price, String contents,Date createAt,String uri,String publisher,String dong){
        this.title=title;
        this.dong=dong;
        this.price=price;
        this.contents=contents;
        this.createAt=createAt;
        this.uri=uri;
        this.publisher=publisher;
    }

    public String getTitle() { return this.title;}
    public void setTitle(String title) {this.title=title;}
    public Number  getPrice() { return this.price;}
    public void setPrice(int price) {this.price=price;}
    public String getContents() { return this.contents;}
    public void setContents(String contents) {this.contents=contents;}
    public Date getCreateAt() { return this.createAt;}
    public void setCreateAt(Date createAt) {this.createAt=createAt;}
    public String getUri() { return this.uri;}
    public void setUri(String uri) {this.uri=uri;}
    public String getPublisher() { return this.publisher;}
    public void setPublisher(String publisher) {this.publisher=publisher;}
    public String getDong() { return this.dong;}
    public void setDong(String dong) {this.dong=dong;}

}

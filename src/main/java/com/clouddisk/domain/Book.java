package com.clouddisk.domain;

/**
 * Created by kingfou on 2018/11/19.
 */
public class Book  {
    private int price;
    private int id;
    private String name;
    private String publish;

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {

        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublish() {
        return publish;
    }

    public String getAuthor() {
        return author;
    }

    private String author;

}

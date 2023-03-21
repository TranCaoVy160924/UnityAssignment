package com.example.blablabla.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name;
    private String description;
    private String image;
    private int numberOfPage;
    private String author;

    public Book(int id, String name, String description, String image, int numberOfPage, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.numberOfPage = numberOfPage;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

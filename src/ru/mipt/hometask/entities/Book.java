package ru.mipt.hometask.entities;

import java.util.Date;

public class Book{
    private static int currentId;

    protected int id;
    protected String author;
    protected String title;
    protected Date publishingYear;

    static {
        currentId = 1;
    }

    public Book(int id, String author, String title, Date publishingYear) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publishingYear = publishingYear;
    }

    public Book(String author, String title, Date publishingYear) {
        this.id = getCurrentId();
        this.author = author;
        this.title = title;
        this.publishingYear = publishingYear;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Date publishingYear) {
        this.publishingYear = publishingYear;
    }

    public static int getCurrentId() {
        return currentId++;
    }
}

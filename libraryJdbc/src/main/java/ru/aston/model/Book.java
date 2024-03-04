package ru.aston.model;

public class Book {
    private long id;
    private String title;
    private String author;
    private int available;
    private int total;
    public Book(){}

    public Book(String title, String author, int available, int total) {
        this.title = title;
        this.author = author;
        this.available = available;
        this.total = total;
    }

    public Book(long id, String title, String author, int available, int total) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}


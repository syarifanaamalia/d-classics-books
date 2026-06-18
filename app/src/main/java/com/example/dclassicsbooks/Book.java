package com.example.dclassicsbooks;

public class Book {
    private String title, author, price, category, synopsis;
    private int image, ratingImage;

    public Book(String title, String author, String price, int image, int ratingImage, String category, String synopsis) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
        this.ratingImage = ratingImage;
        this.category = category;
        this.synopsis = synopsis;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPrice() { return price; }
    public int getImage() { return image; }
    public int getRatingImage() { return ratingImage; }
    public String getCategory() { return category; }
    public String getSynopsis() { return synopsis; }
}
package com.example.akipuja.Inclass7;
/*
  Naga Poorna Pujitha,
          Akshay Karai
          Group34
*/
import java.io.Serializable;

public class NewsArticle implements Serializable{
    String title;
    String publishedAt;
    String urlToImage;
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

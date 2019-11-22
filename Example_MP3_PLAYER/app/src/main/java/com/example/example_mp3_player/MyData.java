package com.example.example_mp3_player;

public class MyData {
    private String ablumId;
    private String singerId;
    private String imageView;

    public MyData(String ablumId, String singerId, String imageView) {
        this.ablumId = ablumId;
        this.singerId = singerId;
        this.imageView = imageView;
    }

    public MyData(String ablumId, String singerId) {
        this.ablumId = ablumId;
        this.singerId = singerId;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getAblumId() {
        return ablumId;
    }

    public void setAblumId(String ablumId) {
        this.ablumId = ablumId;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }
}

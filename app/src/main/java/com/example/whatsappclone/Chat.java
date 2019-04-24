package com.example.whatsappclone;

public class Chat {
    String Title;
    String image;

    public Chat(String Title, String image) {
        this.Title = Title;
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

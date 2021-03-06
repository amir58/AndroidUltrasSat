package com.amirmohammed.androidultrassat.firebase;

public class User {

    private String name;
    private String phone;
    private String email;
    private String imageUrl;

    public User(String name, String phone, String email, String imageUrl) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

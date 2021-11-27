package com.amirmohammed.androidultrassat.ui;

public class CoffeeModel {
    public String name;
    public int price;
    public String imageUrl;
    public int quantity = 0;

    public CoffeeModel(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}

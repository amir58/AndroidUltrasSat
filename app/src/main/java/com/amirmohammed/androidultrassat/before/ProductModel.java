package com.amirmohammed.androidultrassat.before;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private int id;
    private String name;
    private String description;
    private String sellerName;
    private int sellerId;
    private double price;
    private int quantity;
//    private String images[];
//    private String sizes[];
//    private String colors[];

    public ProductModel(int id, String name, String description, String sellerName, int sellerId, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\nProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerId=" + sellerId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

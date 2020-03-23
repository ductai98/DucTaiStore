package com.example.dtstore.models;

public class Cart {
    private int id;
    private String productName;
    private long totalPrice;
    private String productImage;
    private int count;

    public Cart(int id, String productName, long totalPrice, String productImage, int count) {
        this.id = id;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.productImage = productImage;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

package com.FoodDelivery;

public class User {
    private int userId;
    private String name;
    private String phone;
    private Cart cart;

    // Constructor
    public User(int userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.cart = new Cart();  // Each user gets their own cart
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return cart;
    }
}

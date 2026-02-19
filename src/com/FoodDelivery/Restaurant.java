package com.FoodDelivery;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private int restaurantId;
    private String name;
    private List<FoodItem> menu;

    // Constructor
    public Restaurant(int restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.menu = new ArrayList<>();
    }

    // Add item to menu
    public void addFoodItem(FoodItem item) {
        menu.add(item);
    }

    // Show menu
    public void displayMenu() {
        System.out.println("\nMenu for " + name + ":");
        for (FoodItem item : menu) {
            item.displayItem();
        }
    }

    public String getName() {
        return name;
    }

    public List<FoodItem> getMenu() {
        return menu;
    }
}

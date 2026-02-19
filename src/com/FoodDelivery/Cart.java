package com.FoodDelivery;

import java.util.*;

public class Cart {

    private Map<FoodItem, Integer> items;

    public Cart() {
        items = new LinkedHashMap<>(); // keeps order
    }

    // Add item
    public void addItem(FoodItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        System.out.println(quantity + " x " + item.getName() + " added to cart.");
    }

    // Show cart with index
    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        int index = 1;
        System.out.println("\nItems in your cart:");
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            FoodItem item = entry.getKey();
            int qty = entry.getValue();
            System.out.println(index + ". " + item.getName() +
                    " x " + qty + " = Rs." + (item.getPrice() * qty));
            index++;
        }
    }

    // Remove item completely using cart index
    public void removeItem(int index) {
        if (index <= 0 || index > items.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        FoodItem item = new ArrayList<>(items.keySet()).get(index - 1);
        items.remove(item);
        System.out.println(item.getName() + " removed from cart.");
    }

   public void updateQuantity(int index, int newQuantity) {

        if (index <= 0 || index > items.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        FoodItem item = new ArrayList<>(items.keySet()).get(index - 1);

        if (newQuantity <= 0) {
            items.remove(item);
            System.out.println(item.getName() + " removed from cart.");
        } else {
            items.put(item, newQuantity);
            System.out.println("Quantity updated to " + newQuantity +
                    " for " + item.getName());
        }
    }


    public double calculateTotal() {
        double total = 0;

        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }
}

package com.FoodDelivery;

import java.io.*;
import java.util.Map;

public class Order {

    private static int orderCounter = loadLastOrderId();

    private int orderId;
    private User user;
    private double totalAmount;

    private Restaurant restaurant;

    public Order(User user, Restaurant restaurant) {
        this.orderId = orderCounter++;
        this.user = user;
        this.restaurant = restaurant;
        this.totalAmount = user.getCart().calculateTotal();
    }


   private static int loadLastOrderId() {
        int lastId = 0;

        try {
            File file = new File("orders.txt");

            if (!file.exists()) {
                return 1;   // First order
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Order ID:")) {
                    String[] parts = line.split(":");
                    lastId = Integer.parseInt(parts[1].trim());
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading order file.");
        }
        return lastId + 1;
    }

    public void displayOrder() {
        System.out.println("\n----- Order Summary -----");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + user.getName());

        for (Map.Entry<FoodItem, Integer> entry :
                user.getCart().getItems().entrySet()) {

            FoodItem item = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(item.getName() + " x " + quantity +
                    " = Rs." + (item.getPrice() * quantity));
        }

        System.out.println("Total Amount: Rs." + totalAmount);
        System.out.println("--------------------------");
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void saveToFile(String paymentMethod) {

        try {
            File file = new File("orders.txt");
            FileWriter writer = new FileWriter(file, true);

            writer.write("Order ID: " + orderId + "\n");
            writer.write("Customer: " + user.getName() + "\n");
            writer.write("Restaurant: " + restaurant.getName() + "\n");

            for (Map.Entry<FoodItem, Integer> entry :
                    user.getCart().getItems().entrySet()) {

                FoodItem item = entry.getKey();
                int quantity = entry.getValue();

                writer.write(item.getName() + " x " + quantity +
                        " = Rs." + (item.getPrice() * quantity) + "\n");
            }

            writer.write("Total: Rs." + totalAmount + "\n");
            writer.write("Payment Method: " + paymentMethod + "\n");
            writer.write("----------------------------\n");

            writer.close();

            //System.out.println("Order saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error saving order.");
        }
    }
}

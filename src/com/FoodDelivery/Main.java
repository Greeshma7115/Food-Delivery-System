package com.FoodDelivery;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ---------------- RESTAURANTS ----------------

        Restaurant r1 = new Restaurant(1, "Tasty Bites");
        r1.addFoodItem(new FoodItem(1, "Burger", 120));
        r1.addFoodItem(new FoodItem(2, "Pizza", 250));
        r1.addFoodItem(new FoodItem(3, "Pasta", 180));
        r1.addFoodItem(new FoodItem(4, "Fries", 90));

        Restaurant r2 = new Restaurant(2, "Spicy Hub");
        r2.addFoodItem(new FoodItem(1, "Biryani", 220));
        r2.addFoodItem(new FoodItem(2, "Chicken Curry", 300));
        r2.addFoodItem(new FoodItem(3, "Naan", 40));
        r2.addFoodItem(new FoodItem(4, "Paneer Tikka", 200));

        Restaurant r3 = new Restaurant(3, "Healthy Bowl");
        r3.addFoodItem(new FoodItem(1, "Salad", 150));
        r3.addFoodItem(new FoodItem(2, "Smoothie", 120));
        r3.addFoodItem(new FoodItem(3, "Fruit Bowl", 180));
        r3.addFoodItem(new FoodItem(4, "Oats Meal", 130));

        Restaurant r4 = new Restaurant(4, "Sweet Treats");
        r4.addFoodItem(new FoodItem(1, "Ice Cream", 90));
        r4.addFoodItem(new FoodItem(2, "Brownie", 110));
        r4.addFoodItem(new FoodItem(3, "Milkshake", 140));
        r4.addFoodItem(new FoodItem(4, "Cake Slice", 160));

        // ---------------- USER INPUT ----------------

        String name;
        while (true) {
            System.out.print("Enter your name: ");
            name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name is mandatory.");
            } else {
                break;
            }
        }

        String phone;
        while (true) {
            System.out.print("Enter your phone number: ");
            phone = sc.nextLine().trim();

            if (phone.isEmpty()) {
                System.out.println("Mobile number is mandatory.");
            } else if (!phone.matches("\\d{10}")) {
                System.out.println("Invalid mobile number. Must be 10 digits.");
            } else {
                break;
            }
        }

        User user = new User(1, name, phone);

        // ---------------- RESTAURANT SELECTION ----------------

        System.out.println("\nSelect Restaurant:");
        System.out.println("1. Tasty Bites");
        System.out.println("2. Spicy Hub");
        System.out.println("3. Healthy Bowl");
        System.out.println("4. Sweet Treats");

        int restaurantChoice = sc.nextInt();

        Restaurant restaurant;

        switch (restaurantChoice) {
            case 1:
                restaurant = r1;
                break;
            case 2:
                restaurant = r2;
                break;
            case 3:
                restaurant = r3;
                break;
            case 4:
                restaurant = r4;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Tasty Bites.");
                restaurant = r1;
        }

        // ---------------- ORDER LOOP ----------------

        int choice;

        do {
            restaurant.displayMenu();

            int menuSize = restaurant.getMenu().size();

            System.out.println((menuSize + 1) + ". View Cart & Checkout");
            System.out.println((menuSize + 2) + ". Manage Quantity");
            System.out.println(0 + ". Exit");

            System.out.print("Choose option: ");
            choice = sc.nextInt();

            if (choice >= 1 && choice <= restaurant.getMenu().size()) {

                FoodItem selectedItem = restaurant.getMenu().get(choice - 1);

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                if (quantity > 0) {
                    user.getCart().addItem(selectedItem, quantity);
                } else {
                    System.out.println("Invalid quantity.");
                }

            } else if (choice == 5) {

                if (user.getCart().isEmpty()) {
                    System.out.println("Cart is empty. Add items before checkout.");
                } else {
                    break;
                }

            } else if (choice == 6) {

                if (user.getCart().isEmpty()) {
                    System.out.println("Cart is empty.");
                } else {

                    user.getCart().displayCart();

                    System.out.print("Enter cart item number: ");
                    int index = sc.nextInt();

                    System.out.println("1. Increase Quantity");
                    System.out.println("2. Decrease Quantity");
                    int action = sc.nextInt();

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    if (action == 1) {
                        user.getCart().updateQuantity(index, qty);
                    } else if (action == 2) {
                        user.getCart().updateQuantity(index, -qty);
                    } else {
                        System.out.println("Invalid option.");
                    }
                }

            } else if (choice == 0) {
                System.out.println("Thank you for visiting!");
                sc.close();
                return;
            } else {
                System.out.println("Invalid choice.");
            }

        } while (true);

        // ---------------- ORDER SUMMARY ----------------

        Order order = new Order(user, restaurant);
        order.displayOrder();

        // ---------------- PAYMENT ----------------

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash on Delivery");
        System.out.println("4. Cancel Order");

        int paymentChoice = sc.nextInt();

        Payment payment = null;
        String paymentMethod = "";

        switch (paymentChoice) {
            case 1:
                payment = new UpiPayment();
                paymentMethod = "UPI";
                break;
            case 2:
                payment = new CardPayment();
                paymentMethod = "Card";
                break;
            case 3:
                payment = new CashOnDelivery();
                paymentMethod = "Cash on Delivery";
                break;
            case 4:
                System.out.println("Order cancelled.");
                sc.close();
                return;
            default:
                System.out.println("Invalid payment option.");
                sc.close();
                return;
        }

        payment.pay(order.getTotalAmount());
        order.saveToFile(paymentMethod);

        System.out.println("\nOrder placed successfully! 🎉");

        sc.close();
    }
}

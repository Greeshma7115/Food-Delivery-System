package com.FoodDelivery;

public class CardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Payment of Rs" + amount + " done using Card.");
    }
}

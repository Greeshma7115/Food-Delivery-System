package com.FoodDelivery;


    public class CashOnDelivery implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Payment of Rs" + amount + " will be collected on delivery.");
    }
}


package com.company;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> orders;
    private double totalPrice = 0;
    private Timestamp timestamp;
    private int orderNumber = 0;

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order() {
        orders = new ArrayList<>();
    }

    public ArrayList<Pizza> getOrders() {
        return orders;
    }

    public void addPizzaToOrder(Pizza pizza) {
        orders.add(pizza);
    }

    public void setTotalPrice() {
        Pizza selectedPizza = null;
        for (int i = 0; i < orders.size(); i++) {
            selectedPizza = orders.get(i);
            totalPrice += selectedPizza.getPrice();
        }
    }

    public String toString() {
        String temp = "Order number - " + orderNumber + "\n";
        for (int i = 0; i < orders.size(); i++) {
            temp += orders.get(i) + "\n";
        }
        String StringPrice = String.valueOf(totalPrice);
        temp += "Total - " + StringPrice + " kr.\n";
        return temp;
    }
}

package com.company;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> orders;
    private double totalPrice = 0;
    private LocalDateTime localDateTime;
    private int orderNumber = 0;

    //fjern pizza fra ordre
    //tilføj pizza til ordre
    //tilføj note til ordre


    public Order() {
        orders = new ArrayList<>();
        localDateTime = LocalDateTime.now();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String dateToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd–MM–yy");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }


    public String toString() {
        String temp = "Order number - " + orderNumber + "\n";
        for (int i = 0; i < orders.size(); i++) {
            temp += orders.get(i) + "\n";
        }
        String stringPrice = String.valueOf(totalPrice);
        temp += "Total - " + stringPrice + " kr.\n";
        return temp + dateToString();
    }
}

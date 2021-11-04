package com.company;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderList;

    public OrderList() {
        orderList = new ArrayList<>();
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void addOrderToList(Order order) {
        orderList.add(order);
    }

    @Override
    public String toString() {
        String temp = "";
        for (Order order: orderList) {
            temp += "\n" + order;
        }
        return temp;
    }
}
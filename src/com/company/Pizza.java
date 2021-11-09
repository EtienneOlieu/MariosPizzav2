package com.company;


public class Pizza {
    private int number;
    private String name;
    private String ingredients;
    private double price;
    private int salesCounter = 0;

    public Pizza(int number, String name, String ingredients, double price) {
        this.number = number;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void countSale() {
        salesCounter++;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + " - " + name + " - " + ingredients + " - " + price + " kr. ";
    }

    public String toCSVString() {
        return number + ";" + name + ";" + ingredients + ";" + price;
    }

    public String showSales() {
        return number + " - " + name + " - sold today: " + salesCounter;
    }
}




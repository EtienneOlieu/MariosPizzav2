package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
    private Pizza pizza;
    private ArrayList<Pizza> menuList;

    public Menu() {
        menuList = new ArrayList<>();

    }

    public ArrayList<Pizza> getMenuList() {
        return menuList;

    }

    public void loadMenu() {
        File file = new File("data/menu.csv");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] menuLine = sc.nextLine().split(";");
                createNewPizza(menuList.size() + 1, menuLine[1], menuLine[2], Double.parseDouble(menuLine[3]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(file + " was not found: " + e);
        }
    }

    public void createNewPizza(int number, String name, String ingredients, double price) {
        Pizza pizza = new Pizza(number, name, ingredients, price);
        menuList.add(pizza);
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < menuList.size(); i++) {
            temp += menuList.get(i) + "\n";
        }
        return temp;
    }

    public String salesOfTheDay(){
    String temp = "";
        for (Pizza pizza:menuList) {
            temp += pizza.showSales()+"\n";
        }
    return temp;
    }

}

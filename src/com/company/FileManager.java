package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class FileManager {
    public void saveToMenu(Menu pizzaMenu) {

        File file = new File("data/menu.csv");
        try {
            PrintStream ps = new PrintStream(file);

            for (Pizza pizza : pizzaMenu.getMenuList()) {
                ps.println(pizza.toCSVString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveToOrderHistory(Order order) {
        File file = new File("data/orderhistory.csv");
        if (order !=null) {
            try {
                PrintStream ps = new PrintStream(new FileOutputStream(file, true));
                String temp = "Order number - " + order.getOrderNumber() + "\n";
                for (int i = 0; i < order.getOrders().size(); i++) {
                    temp += order.getOrders().get(i).toCSVString() + "\n";
                }
                order.setLocalDateTime(LocalDateTime.now());
                String StringPrice = String.valueOf(order.getTotalPrice());
                temp += "Total - " + StringPrice + " kr.\n" + order.dateToString() + "\n\n";

                ps.append(temp);
                ps.close();

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

package com.company;

import java.util.Scanner;

public class MariosPizza {

    private FileManager fileManager;
    private UserInterface ui;
    private OrderList orderList;
    private int orderCounter = 0;

    Scanner sc = new Scanner(System.in);

    public MariosPizza() {
        fileManager = new FileManager();
        ui = new UserInterface(this);
        orderList = new OrderList();
    }

    public void start() {
        Menu menu = new Menu();
        boolean goAgain = true;
        menu.loadMenu();

        while (goAgain) {
            ui.welcomeMessage();
            int command = sc.nextInt();
            sc.nextLine();
            switch (command) {
                case 1 -> ui.showMenu(menu);
                case 2 -> orderPizza(menu);
                case 3 -> showOrderList();
                case 4 -> finalizeOrder();
                case 9 -> createNewPizza(menu);
                case 0 -> {
                    fileManager.saveToMenu(menu);
                    System.out.println("Pizzamenu has been saved.");
                    goAgain = false;
                }
            }
        }
    }

    private void createNewPizza(Menu menu) {
        ui.createNewPizzaPrints(1);
        String name = sc.nextLine();
        ui.createNewPizzaPrints(2);
        String ingredients = sc.nextLine();
        ui.createNewPizzaPrints(3);
        double price = sc.nextInt();
        menu.createNewPizza(menu.getMenuList().size() + 1, name, ingredients, price);
    }

    private void finalizeOrder() {
        Order selectedOrder = null;
        ui.finalizeOrderPrints(orderList);
        System.out.println("Select the order number to finalize"); //TODO Nederen lille sout.
        int tempCommand = sc.nextInt();
        for (Order order : orderList.getOrderList()) {
            if (order.getOrderNumber() == tempCommand) {
                selectedOrder = order;
                fileManager.saveToOrderHistory(selectedOrder);
            }
        }
        orderList.getOrderList().remove(selectedOrder);
    }


    private void showOrderList() {
        ui.finalizeOrderPrints(orderList);
    }

    public void orderPizza(Menu menu) {
        Order order = new Order();
        Pizza selectedPizza;
        ui.showMenu(menu);
        ui.orderPizzaPrints(1);
        int command = -1;

        while (command != 0) {

            command = sc.nextInt();
            if (command < menu.getMenuList().size() && command > 0) {
                ui.orderPizzaPrints(2);
                selectedPizza = menu.getMenuList().get(command - 1);
                order.addPizzaToOrder(selectedPizza);
            } else if (command != 0) {
                ui.orderPizzaPrints(4);
            }
        }
        order.setTotalPrice();
        orderCounter++;
        order.setOrderNumber(orderCounter);
        ui.orderPizzaPrints(3);
        ui.printOrder(order);
        orderList.addOrderToList(order);

    }
}

package com.company;
//Dette er min controller

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
            System.out.println(ui.welcomeMessage());
            int command = sc.nextInt();
            sc.nextLine();
            switch (command) {
                case 1 -> ui.showMenu(menu);
                case 2 -> orderPizza(menu);
                case 3 -> showOrderList();
                case 4 -> finalizeOrder();
                case 9 -> ui.createNewPizza(menu);
                case 0 -> {
                    fileManager.saveToMenu(menu);
                    System.out.println("Pizzamenu has been saved.");
                    goAgain = false;
                }
                default -> System.out.println("Please enter a valid command.");
            }
        }
    }

    private void finalizeOrder() {
        Order selectedOrder = null;
        ui.finalizeOrder(orderList);
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
        ui.finalizeOrder(orderList);
    }

    public void orderPizza(Menu menu) {
        Order order = new Order();
        Pizza selectedPizza;
        ui.showMenu(menu);
        ui.orderPizza(1);
        int command = -1;
        while (command != 0) {
            command = sc.nextInt();
            if (command < menu.getMenuList().size() && command > 0) {
                ui.orderPizza(2);
                selectedPizza = menu.getMenuList().get(command - 1);
                order.addPizzaToOrder(selectedPizza);
            } else if (command != 0) {
                System.out.println("There is no such pizza.");
            }
        }
        order.setTotalPrice();
        orderCounter++;
        order.setOrderNumber(orderCounter);
        ui.orderPizza(3);
        ui.printOrder(order);
        orderList.addOrderToList(order);

    }
}

package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MariosPizza {

    private FileManager fileManager;
    private UserInterface ui;
    private OrderList orderList;
    private int orderCounter = 0;
    private Order selectedOrder = null;
    private Pizza selectedPizza = null;
    private Menu menu;

    Scanner sc = new Scanner(System.in);

    public MariosPizza() {
        fileManager = new FileManager();
        ui = new UserInterface(this);
        orderList = new OrderList();
        menu = new Menu();
    }

    public void start() {
        boolean goAgain = true;
        menu.loadMenu();

        while (goAgain) {
            ui.welcomeMessage();
            try {
                int command = sc.nextInt();
                sc.nextLine();
                switch (command) {
                    case 1 -> ui.showMenu(menu);
                    case 2 -> orderPizza(menu);
                    case 3 -> showOrderList();
                    case 4 -> finalizeOrder();
                    case 5 -> removeOrder();
                    case 6 -> ui.printSalesOfTheDay(menu.salesOfTheDay());
                    case 9 -> createNewPizza(menu);
                    case 10 -> removePizzaFromMenu();
                    case 0 -> {
                        fileManager.saveToMenu(menu);
                        System.out.println("Pizzamenu has been saved.");
                        goAgain = false;
                        //TODO sørg for at sales printer ud med bedste salg først
                    }
                    default -> System.out.println("glitch");
                }
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Please type a number.");
            }
        }
    }

    private void createNewPizza(Menu menu) {
        ui.userInterfacePrints(1);
        String name = sc.nextLine();
        ui.userInterfacePrints(2);
        String ingredients = sc.nextLine();
        ui.userInterfacePrints(3);
        double price = sc.nextInt();
        menu.createNewPizza(menu.getMenuList().size() + 1, name, ingredients, price);
    }

    public void selectPizza(){
        ui.showMenu(menu);
        ui.userInterfacePrints(9);
        int command = -1;

        while (command != 0) {
            try {
                command = sc.nextInt();
                if (command <= menu.getMenuList().size() && command > 0) {
                    selectedPizza = menu.getMenuList().get(command - 1);
                    command = 0;
                } else if (command != 0) {
                    ui.userInterfacePrints(7);
                }
            } catch (InputMismatchException e) {
                command = -1;
                sc.nextLine();
            }
        }
    }

    public void removePizzaFromMenu(){ //TODO flyt til menu
        selectPizza();
        menu.getMenuList().remove(selectedPizza);
        fixPizzaNumbers();
        selectedPizza = null;
    }

    public void fixPizzaNumbers(){ //TODO flyt til menu
        for (Pizza pizza: menu.getMenuList()) {
           if ( pizza.getNumber() >= selectedPizza.getNumber()) {
                pizza.setNumber(pizza.getNumber() - 1);
            }
        }
    }

    private void selectOrder() {
        ui.finalizeOrderPrints(orderList);
        ui.userInterfacePrints(8);
            int tempCommand = sc.nextInt();
            for (Order order : orderList.getOrderList()) {
                if (order.getOrderNumber() == tempCommand) {
                    selectedOrder = order;
                }
            }

    }

    private void finalizeOrder() {
        selectOrder();
        fileManager.saveToOrderHistory(selectedOrder);
        for (Pizza pizza : selectedOrder.getOrders()) {
            pizza.countSale();
        }
       removeOrder();
    }


    private void removeOrder() {
        if (selectedOrder == null){
        selectOrder();
        }
        orderList.getOrderList().remove(selectedOrder);
        selectedOrder = null;
    }

    private void showOrderList() {
        ui.finalizeOrderPrints(orderList);
    }

    public void orderPizza(Menu menu) {
        Order order = new Order();
        ui.showMenu(menu);
        ui.userInterfacePrints(4);
        int command = -1;

        while (command != 0) {
            try {
                command = sc.nextInt();
                if (command <= menu.getMenuList().size() && command > 0) {
                    ui.userInterfacePrints(5);
                    selectedPizza = menu.getMenuList().get(command - 1);
                    order.addPizzaToOrder(selectedPizza);
                    selectedPizza = null;
                } else if (command != 0) {
                    ui.userInterfacePrints(7);
                }
            } catch (InputMismatchException e) {
                command = -1;
                sc.nextLine();
            }
        }
                order.setTotalPrice();
                orderCounter++;
                order.setOrderNumber(orderCounter);
                ui.userInterfacePrints(6);
                ui.printOrder(order);
                orderList.addOrderToList(order);
    }
}

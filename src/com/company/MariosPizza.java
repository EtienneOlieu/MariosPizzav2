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
        menu.loadMenu();
        mainMenu();
    }

    public void mainMenu() {
        boolean goAgain = true;
        while (goAgain) {
            ui.switchMessage(1);
            try {
                int command = sc.nextInt();
                sc.nextLine();
                switch (command) {
                    case 1 -> ui.showMenu(menu);
                    case 2 -> orderPizza(menu);
                    case 3 -> {
                        showOrderList();
                        orderMenu();
                    }
                    case 4 -> statsMenu();
                    case 5 -> editMenuMenu();
                    case 0 -> {
                        fileManager.saveToMenu(menu);
                        System.out.println("Pizzamenu has been saved.");
                        goAgain = false;
                        //TODO sørg for at sales printer ud med bedste salg først
                    }
                    default -> System.out.println("Wrong input.");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please type a number.");
            }
        }
    }

    public void orderMenu() {
        ui.switchMessage(2);
        int command = sc.nextInt();
        sc.nextLine();
        switch (command) {
            case 1 -> finalizeOrder();
            case 2 -> editOrder();
            case 3 -> removeOrder();
        }
    }

    public void editOrder() {
        ui.switchMessage(3);
        int command = sc.nextInt();
        sc.nextLine();
        switch (command) {
            case 1 -> addExtraPizzaToOrder();
            case 2 -> removePizzaFromOrder();
            //todo   case 3 -> noteToOrder();
        }
    }

    public void statsMenu() {
        ui.switchMessage(4);
        int command = sc.nextInt();
        sc.nextLine();
        switch (command) {
            case 1 -> ui.printSalesOfTheDay(menu.salesOfTheDay());
        }
    }

    public void editMenuMenu() {
        ui.switchMessage(5);
        int command = sc.nextInt();
        sc.nextLine();
        switch (command) {
            case 1 -> createNewPizza(menu);
            case 2 -> removePizzaFromMenu();
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

    public void selectPizza() {
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

    public void orderPizza(Menu menu) {//TODO samme metode (while) som i select pizza
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
        ui.showOrder(order);
        orderList.addOrderToList(order);
    }

    public void addExtraPizzaToOrder() {
        selectOrder();
        ui.showMenu(menu);
        ui.userInterfacePrints(8);
        selectPizza();
        selectedOrder.addPizzaToOrder(selectedPizza);
        selectedOrder.setTotalPrice();
    }

    public void removePizzaFromOrder() {
        selectOrder();
        ui.showOrder(selectedOrder);
        ui.userInterfacePrints(9);
        selectPizza();
        selectedOrder.removePizzaFromOrder(selectedPizza);
        selectedOrder.setTotalPrice();
    }

    public void removePizzaFromMenu() { //TODO flyt til menu
        ui.showMenu(menu);
        ui.userInterfacePrints(13);
        selectPizza();
        menu.getMenuList().remove(selectedPizza);
        fixPizzaNumbers();
        selectedPizza = null;
    }

    public void fixPizzaNumbers() { //TODO flyt til menu
        for (Pizza pizza : menu.getMenuList()) {
            if (pizza.getNumber() >= selectedPizza.getNumber()) {
                pizza.setNumber(pizza.getNumber() - 1);
            }
        }
    }

    private void selectOrder() {
        ui.finalizeOrderPrints(orderList);
        ui.userInterfacePrints(12);
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
        if (selectedOrder == null) {
            selectOrder();
        }
        orderList.getOrderList().remove(selectedOrder);
        selectedOrder = null;
    }

    private void showOrderList() {
        ui.finalizeOrderPrints(orderList);
    }


}

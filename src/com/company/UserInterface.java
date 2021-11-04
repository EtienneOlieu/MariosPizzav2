package com.company;

import java.util.Scanner;

public class UserInterface {
    private MariosPizza mariosPizza;
    Scanner scanner = new Scanner(System.in);

    public UserInterface(MariosPizza mariosPizza) {
        this.mariosPizza = mariosPizza;
    }

    public String welcomeMessage() {
        return """

                Press ´1´ to show menu.
                Press ´2´ create new order.
                Press ´3´ show open orders.
                Press '4' to finalize order.
                Press '9' to create a new pizza.
                Press ´0´ to quit the program.""";
    }

    public void showMenu(Menu menu) {
        System.out.println(menu);
    }

    public void createNewPizza(Menu menu) {
        System.out.println("Write the name of the pizza: ");
        String name = scanner.nextLine();
        System.out.println("Write the ingredients for the pizza: ");
        String ingredients = scanner.nextLine();
        System.out.println("Write the price of the pizza: ");
        double price = scanner.nextInt();
        menu.createNewPizza(menu.getMenuList().size() + 1, name, ingredients, price);
    }

    public void orderPizza(int number) {
        switch (number) {
            case 1 -> System.out.println("Select the number for the pizza you want to add to your order");
            case 2 -> System.out.println("Press '0' to finish the order");
            case 3 -> System.out.println("The final order is: ");
        }
    }

    public void printOrder(Order order) {
        System.out.println(order);
    }

    public void finalizeOrder(OrderList orderList){
        System.out.println(orderList);
        System.out.println("Select the order number to finalize");
    }

}

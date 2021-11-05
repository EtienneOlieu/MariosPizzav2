package com.company;
public class UserInterface {

    private MariosPizza mariosPizza;

    public UserInterface(MariosPizza mariosPizza) {
        this.mariosPizza = mariosPizza;
    }

    public void welcomeMessage() {
        System.out.println("""

                Press ´1´ to show menu.
                Press ´2´ to create new order.
                Press ´3´ to show open orders.
                Press '4' to finalize order.
                Press ´5´ to remove order.
                Press ´6´ to get sales of the day.
                Press '9' to create a new pizza.
                Press ´0´ to quit the program.""");

    }

    public void showMenu(Menu menu) {
        System.out.println(menu);
    }

    public void userInterfacePrints(int number) {
        switch (number) {
            //Create pizza prints
            case 1 -> System.out.println("Write the name of the pizza: ");
            case 2 -> System.out.println("Write the ingredients for the pizza: ");
            case 3 -> System.out.println("Write the price of the pizza: ");
            // Order pizza prints
            case 4 -> System.out.println("Select the number for the pizza you want to add to your order");
            case 5 -> System.out.println("Press '0' to finish the order");
            case 6 -> System.out.println("The final order is: ");
            case 7 -> System.out.println("There is no such pizza");
            // Finalize order prints
            case 8 -> System.out.println("Select an order");
        }
    }
    public void printOrder(Order order) {
        System.out.println(order);
    }
    public void finalizeOrderPrints(OrderList orderList) {
        System.out.println(orderList);
    }
    public void printSalesOfTheDay(String string) {
        System.out.println(string);
    }
}

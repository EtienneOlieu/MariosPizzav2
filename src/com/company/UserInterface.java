package com.company;
public class UserInterface {

    private MariosPizza mariosPizza;

    public UserInterface(MariosPizza mariosPizza) {
        this.mariosPizza = mariosPizza;
    }

    public void switchMessage(int number) {
        switch (number) {
            case 1 -> System.out.println("""
                    ______________________________________________
                    Press '1' to show menu.
                    Press '2' to create new order.
                    Press '3' to show and edit open orders.
                    Press '4' to show statistics.
                    Press '5' to edit the menu.
                    Press '0' to quit the program.
                    ______________________________________________
                    """);
            case 2 -> System.out.println("""
                    ______________________________________________
                    Press '1' to finalize order.
                    Press '2' to edit an order.
                    Press '3' to remove order.
                    Press 'Anything else' to return to the main menu.
                    ______________________________________________
                    """);
            case 3 -> System.out.println("""
                    ______________________________________________
                    Press '1' to add a pizza to an order.
                    Press '2' to remove a pizza from an order.
                    Press '3' to add a note to an order (VIRKER IKKE ENDNU)
                    Press 'Anything else' to return to the main menu.
                    ______________________________________________
                    """);
            case 4 -> System.out.println("""
                    ______________________________________________
                    Press '1' to see sales of the day.
                    Press 'Anything else' to return to the main menu.
                    ______________________________________________
                    """);
            case 5 -> System.out.println("""
                    ______________________________________________
                    Press '1' to create a new pizza to the menu.
                    Press '2' remove a pizza from the menu.
                    Press 'Anything else' to return to the main menu.
                    ______________________________________________
                    """);
        }
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
            case 8 -> System.out.println("Select the number for the extra pizza you want to add to your order");
            case 9 -> System.out.println("Select the number for the pizza you want to remove from the specific order");
            case 10 -> System.out.println("Add a note to the order");
            // Finalize order prints
            case 12 -> System.out.println("Select an order");
            // Select pizza
            case 13 -> System.out.println("Type the number for the pizza you wish to remove from the menu");
        }
    }

    public void finalizeOrderPrints(OrderList orderList) {
        System.out.println(orderList);
    }
    public void printSalesOfTheDay(String string) {
        System.out.println(string);
    }
    public void showOrder(Order order) {
        System.out.println(order);
    }
}

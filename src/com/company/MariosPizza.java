package com.company;
//Dette er min controller

public class MariosPizza {
    public void start() {
        Menu menu = new Menu();
        menu.loadMenu();

        System.out.println(menu);

        System.out.println(menu.getMenuList().get(14));

    }

}

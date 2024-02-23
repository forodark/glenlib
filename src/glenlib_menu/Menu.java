package glenlib_menu;

import glenlib.In;
import glenlib.Style;
import glenlib.Util;

public class Menu {

    //Defaults
    public static final int MENU_WIDTH = 31;

    public static int getPosition(MenuItem[] items, int choice) {
        int result = 0;
        int valid_options = 0;
        while(valid_options != choice) {
            if (items[result] instanceof Title) {
                result++;
            }
            else if (items[result] instanceof Subtitle) {
                result++;
            }
            else if (items[result] instanceof Line) {
                result++;
            }
            else if (items[result] instanceof Custom) {
                result++;
            }
            else if (items[result] instanceof Option) {
                result++; valid_options++;
            }
        }
        return result-1;
    }

    public static void dontWait() {
        menu_return = 1;
    }

    public static void showMenu(String title, MenuItem[] items) {
        showMenu(title, items, MENU_WIDTH, false);
    }

    public static void showMenu(String title, MenuItem[] items, int menu_width) {
        showMenu(title, items, menu_width, false);
    }

    public static void showMenu(String title, MenuItem[] items, Boolean no_clear) {
        showMenu(title, items, MENU_WIDTH, no_clear);
    }
    
    public static int menu_return = 0;
    
    public static void showMenu(String title, MenuItem[] items, int menu_width, Boolean no_clear) {

        while (true) {
            if (no_clear == false) {
                Util.clear();
                Style.line(menu_width);
            }
            



            if (!title.isEmpty()) {
                Style.printCentered(menu_width, title);
                System.out.println();
                Style.line(menu_width);
            }

            
            int i = 0; int excess = 0;
            for (; i+excess < items.length;) {
                if (items[i+excess] instanceof Title) {
                    Style.line(menu_width);
                    Style.printCentered(menu_width, items[i+excess].getText());
                    Style.nl();
                    Style.line(menu_width);
                    excess++;
                } else if (items[i+excess] instanceof Subtitle) {
                    System.out.println(items[i+excess].getText());
                    excess++;
                } else if (items[i+excess] instanceof Line) {
                    Style.line(menu_width);
                    excess++;
                } else if (items[i+excess] instanceof Custom) {
                    if (!items[i+excess].getText().equals("/hide")) {
                        System.out.println(items[i+excess].getText());
                    }
                    Custom custom = (Custom) items[i+excess];
                    custom.function.execute();
                    excess++;
                } else {
                    if (!items[i+excess].getText().equals("/hide")) {
                        System.out.println("[" + (i + 1) + "] " + items[i+excess].getText());
                    }
                    i++;
                }
                

            }

            System.out.println("[0] Return");
            Style.line(menu_width);

            int choice = In.getInt("Enter Choice: ");

            //this is so that if returned from menu, it doesnt prompt enter to continue
            if (choice == 0) {
                dontWait();
                return;
            }

            if (choice < 0 || choice > i) {
                Util.invalid(Util.INVALID, menu_width);
                continue;
            } 

            Option selected = (Option) items[getPosition(items, choice)];
            selected.function.execute();

            if (menu_return != 1) {
                In.waitEnter();
            }
    
            menu_return = 0;

        }
        
    }

}

/* Sample Usage:
    MenuItem[] items = {
        new Option("Option 1", () -> function1()),
        new Title("test title"),
        new Subtitle("test subtitle"),
        new Line(),
        new Option("Option 2", () -> function2()),
    };

    Menu.showMenu("Menu", items);
 */
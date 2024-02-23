package glenlib;


public class Style {

    //Defaults
    public static final int LINE_WIDTH = 31;

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void printColor(String color, String format, Object... args) {
        String formatted_text = String.format(format, args);
        System.out.print(color + formatted_text + RESET);
    }
    public static String color(String color, String format, Object... args) {
        String formatted_text = String.format(format, args);
        return (color + formatted_text + RESET);
    }
    public static void color(String color) {
        System.out.print(color);
    }
    public static void color() {
        System.out.print(RESET);
    }

    public static void line(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void line() {
        line(LINE_WIDTH);
    }

    public static void printCentered(int width, String format, Object... args) {

        String formatted_text = String.format(format, args);
    
        if (width <= formatted_text.length()) {
            System.out.print(formatted_text);
        } else {
            int total_padding = width - formatted_text.length();
            int left_padding = total_padding / 2;
            int right_padding = total_padding - left_padding;
    
            StringBuilder centered_text = new StringBuilder();
            for (int i = 0; i < left_padding; i++) {
                centered_text.append(" ");
            }
            centered_text.append(formatted_text);
            for (int i = 0; i < right_padding; i++) {
                centered_text.append(" ");
            }
    
            System.out.print(centered_text.toString());
        }
    }

    public static void printTitle(String title) {
        printTitle(LINE_WIDTH, title);
    }

    public static void printTitle(int width, String title) {
        Style.line(width);
        Style.printCentered(width, title);
        Style.nl();
        Style.line(width);
    }
    
    public static void nl() {
        System.out.println();
    }

    public static void print(Object value) {
        System.out.print(value);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void println(Object value) {
        System.out.println(value);
    }
}


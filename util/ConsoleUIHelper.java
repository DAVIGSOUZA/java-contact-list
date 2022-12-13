package util;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUIHelper {

    public static final int UI_WIDTH = 60;
    public static final char UI_PAD = ' ';

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static String askSimpleInput(String message){
        System.out.printf("%s%n# : ", message);
        return new Scanner(System.in).nextLine().trim();
    }

    public static String askNoEmptyInput(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        int tries = 0;
        do {
            System.out.printf("%s%n# : ", message);
            input = sc.nextLine().trim();
            tries++;
        } while (input.isBlank() && tries < 3);
        return input;
    }

    public static int askChooseOption(String message, String... options) {
        System.out.printf("%s%n# : ", message);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d - %s%n# : ", i, options[i]);
        }

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                choice = -9;
            }
        } while (choice < 0 || choice >= options.length);
        return choice;
    }

    public static boolean askConfirm(String message, String yes, String no) {
        return askChooseOption(message, yes, no) == 0;
    }

    public static BigDecimal askNumber(String message) {
        System.out.printf("%s%n# : ", message);
        Scanner sc = new Scanner(System.in);
        BigDecimal number;
        do {
            try {
                number = sc.nextBigDecimal();
            }catch (InputMismatchException e) {
                number = null;
            }
        } while (number == null);
        return number;
    }

    public static int drawWithRightPadding(String text) {
        int count = 0;
        do {
            int limit = Math.min(text.length(), UI_WIDTH - 4);
            String row = text.substring(0, limit);
            text = text.substring(row.length());
            row = "# " + row + String.valueOf(UI_PAD).repeat(UI_WIDTH - row.length() - 4) + " #";
            System.out.println(row);
            count ++;
        } while (!text.isEmpty());
        return count;
    }

    public static void drawWithPadding(String text) {
        do {
            int limit = Math.min(text.length(), UI_WIDTH -4);
            String row = text.substring(0, limit);
            text = text.substring(row.length());
            int padding = ((UI_WIDTH - row.length()) / 2);
            row = "#" + " ".repeat(padding - 1) + row;
            row = row + " ".repeat(UI_WIDTH - row.length() - 1) + "#";
            System.out.println(row);
        } while (!text.isEmpty());
    }

    public static void drawLine() {
        System.out.println("#".repeat(UI_WIDTH));
    }

    public static void drawHeader(String title) {
        drawLine();
        drawWithPadding(title);
        drawLine();

    }

    public static void fillVSpace(int lines) {
        drawWithPadding(" ".repeat(lines * UI_WIDTH));
    }

    public static String columnPaddingLeft(String text){
        while (text.length() < UI_WIDTH) {
            text = String.valueOf(UI_PAD).concat(text);
        }
        return text;
    }

    public static String columnPaddingRight(String text) {
        while(text.length() < UI_WIDTH) {
            text = text.concat(String.valueOf(UI_PAD));
        }
        return text;
    }

    public static String columnPaddingRight(String text, int width) {
        while(text.length() < width) {
            text = text.concat(String.valueOf(UI_PAD));
        }
        return text;
    }
}

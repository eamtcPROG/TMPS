package user.utils;

import java.util.Scanner;
import java.util.List;

public class UIHelper {

    private static Scanner scanner = new Scanner(System.in);

    // Displays a menu with options and reads the user's choice
    public static int displayMenuAndGetChoice(List<String> options) {
        System.out.println("Please choose an option:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        int choice = 0;
        while (choice < 1 || choice > options.size()) {
            System.out.print("Enter your choice (1-" + options.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }

    // Reads a line of input from the user
    public static String readInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Displays a message to the user
    public static void displayMessage(String message) {
        System.out.println(message);
    }
}

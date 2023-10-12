package org.example.app.view;

import org.example.app.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class AppView {

    Scanner scanner = new Scanner(System.in);

    public String getOption() {
        showMenu();
        String option = "";
        try {
            option = scanner.nextLine().trim();
        } catch (InputMismatchException ime) {
            System.out.println(Constants.INCORRECT_VALUE_MSG);
        }
        return option;
    }

    private void showMenu() {
        System.out.print("""
                
                Choose an option
                -------------------
                1 - Products.
                2 - Users.
                0 - Close the App.
                """);
    }

    public void getOutput(String output) {
        if (output.equals(Constants.APP_CLOSE_MSG))
            System.out.println(output);
        scanner.close();
        System.exit(0);
    }
}

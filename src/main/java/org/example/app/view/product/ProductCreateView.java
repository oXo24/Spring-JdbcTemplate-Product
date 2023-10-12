package org.example.app.view.product;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ProductCreateView {

    public String[] getData() {
        Scanner scanner = new Scanner(System.in);
        String title = "Input name: ";
        System.out.print(title);
        String firstName = scanner.nextLine().trim();
        title = "Input quota: ";
        System.out.print(title);
        String lastName = scanner.nextLine().trim();
        title = "Input price in format (xx.xx): ";
        System.out.print(title);
        String phone = scanner.nextLine().trim();
        return new String[]{firstName, lastName, phone};
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}

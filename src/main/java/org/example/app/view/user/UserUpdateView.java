package org.example.app.view.user;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class UserUpdateView {

    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String title = "Input user name: ";
        System.out.print(title);
        map.put("userName", scanner.nextLine().trim());
        title = "Input name: ";
        System.out.print(title);
        map.put("name", scanner.nextLine().trim());
        title = "Input email in format yourname@mail.com: ";
        System.out.print(title);
        map.put("email", scanner.nextLine().trim());
        title = "Input id: ";
        System.out.print(title);
        map.put("id", scanner.nextLine().trim());
        return map;
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}

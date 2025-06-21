package uz.app.service;

import static uz.app.utils.Utill.intScanner;

public class MainService {
    public void service() {
        while (true) {
            System.out.println("""
                    1. Product management
                    2. Shop management
                    3. Company management
                    4. Input output management
                    0. Exit
                    """);
            switch (intScanner.nextInt()) {
                case 0 -> {
                }
                case 1 -> {
                }
                case 2 -> {

                }
                case 3 -> {
                }
                case 4 -> {
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }
}

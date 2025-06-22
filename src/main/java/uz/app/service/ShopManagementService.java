package uz.app.service;

import static uz.app.utils.Utill.intScanner;

public class ShopManagementService {
    public void service() {
        while (true) {
            System.out.print("""
                    1.
                    2.
                    3.
                    
                    0. Exit
                    >>>\s""");
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }
}

package uz.app.service;

import static uz.app.utils.Utill.intScanner;

public class MainService {
    public void service() {
        while (true) {
            System.out.print("""
                    1. Product management
                    2. Shop management
                    3. Company management
                    4. Input output management
                    
                    0. Exit
                    >>>\s""");
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    new ProductManagementService().service();
                }
                case 2 -> {
                    new ShopManagementService().service();
                }
                case 3 -> {
                    new CompanyManagementService().service();
                }
                case 4 -> {
                    new InputOutputService().service();
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }
}

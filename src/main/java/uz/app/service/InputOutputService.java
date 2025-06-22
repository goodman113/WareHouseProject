package uz.app.service;

import static uz.app.utils.Utill.*;

public class InputOutputService {
    public void service() {
        while (true) {
            System.out.print("""
                    1. Input
                    2. Output
                    
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
                case 4 -> {
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }
}

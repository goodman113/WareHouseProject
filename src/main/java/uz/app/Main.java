package uz.app;

import uz.app.service.MainService;

import java.util.Scanner;

import static uz.app.utils.Utill.intScanner;

public class Main {
    public static void main(String[] args) {
        MainService mainService = new MainService();
        while (true) {
            try {
                mainService.service();
            } catch (Exception e) {
                intScanner = new Scanner(System.in);
                System.out.println("Select desired section.");
            }
        }
    }
}
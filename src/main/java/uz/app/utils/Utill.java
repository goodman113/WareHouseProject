package uz.app.utils;

import uz.app.entity.Product;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Utill {
    public static Scanner intScanner = new Scanner(System.in);
    public static Scanner strScanner = new Scanner(System.in);

    public static Product currentProduct;
    public static AtomicInteger number = new AtomicInteger(1);
}

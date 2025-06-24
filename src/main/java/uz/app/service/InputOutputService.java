package uz.app.service;

import uz.app.Main;
import uz.app.db.DataSource;
import uz.app.entity.Input;
import uz.app.entity.InputProduct;
import uz.app.entity.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static uz.app.db.DataSource.*;
import static uz.app.utils.Utill.*;

public class InputOutputService {
    public void service() {
        while (true) {
            System.out.println("""
                    1. Input
                    2. Output
                    
                    0. Exit
                    """);
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    input();
                }
                case 2 -> {

                }
                default -> System.out.println("Select desired section.");
            }
        }
    }

    private void input() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 inputproduct
                    """);
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    inputproduct();
                }
            }
        }
    }

    private void inputproduct() {
//        Main.readinput();
        companies.forEach(company ->
                System.out.println("ID: " + company.getId() + ", Name: " + company.getName())
        );

        System.out.print("company id ni tanlang: ");
        String companyId = strScanner.nextLine();


        List<Product> companyProducts = products
                .stream()
                .filter(product -> product.getCompanyId().equals(companyId))
                .toList();

        if (companyProducts.isEmpty()) {
            System.out.println("company da mahsulot yo'q");
            return;
        }


        companyProducts.forEach(product ->
                System.out.println("ID: " + product.getId() +
                        ", Name: " + product.getName() +
                        ", Price: " + product.getPrice() +
                        ", Count: " + product.getCount())
        );

        System.out.print("product id ni tanlang: ");
        String productId = strScanner.nextLine();

        Product selectedProduct = companyProducts
                .stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (selectedProduct == null) {
            System.out.println("product topilmadi");
            return;
        }

        System.out.print("Nechta blok keldi: ");
        int block = intScanner.nextInt();

        System.out.print("Har bir blokda nechta mahsulot bor: ");
        int countPerBlock = intScanner.nextInt();

        int q = block * countPerBlock;
        double w = q * selectedProduct.getPrice();

        selectedProduct.setCount(selectedProduct.getCount() + q);


        InputProduct inputProduct = new InputProduct(productId, block, countPerBlock);
        List<InputProduct> inputProductList = new ArrayList<>();
        inputProductList.add(inputProduct);
        String date = LocalDate.now().toString();

        Input input = new Input(date, w, companyId, inputProductList);
        inputs.add(input);
//        Main.writeinput();

        System.out.println("Yangi input muvaffaqiyatli qo‘shildi");
    }

}

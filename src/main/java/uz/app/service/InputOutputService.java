package uz.app.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import uz.app.entity.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static uz.app.db.DataSource.*;
import static uz.app.utils.Utill.*;

public class InputOutputService {
    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    static String inputfail = "D:\\Programming\\G54 java\\java_modul_5\\WareHouseProject\\src\\main\\resources\\Inputs.json";
    static String outputFile = "D:\\Programming\\G54 java\\java_modul_5\\WareHouseProject\\src\\main\\resources\\Outputs.json";

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
                    outputService();
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }

    private void outputService() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 send products to shop
                    2 show outputs
                    """);
//            3 show available products
            switch (strScanner.nextLine()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    sendProduct();
                }
                case "2" -> {
                    showOutputs();
                }
//                case "3" -> {
//                    showAvailableProducts();
//                }
            }
        }
    }
//
//    private void showAvailableProducts() {
//        products.forEach(System.out::println);
//    }

    private void showOutputs() {
        outputs.forEach(System.out::println);
    }

    private void sendProduct() {
        double overallPrice = 0.0;
        shops.forEach(System.out::println);
        System.out.println("enter shop id");
        String shopId = strScanner.nextLine();
        Optional<Shop> first = shops.stream()
                .filter(s -> s.getId().equals(shopId))
                .findFirst();
        if (first.isEmpty()) {
            System.out.println("wrong input");
            return;
        }
        List<OutputProduct> outputProducts = getOutputProduct(new ArrayList<>());
        if (outputProducts == null) {
            return;
        }
        while (true) {
            System.out.println("""
                    1 stop and send
                    2 continue with adding products
                    """);
            switch (strScanner.nextLine()) {
                case "1" -> {
                    for (Product product : products) {
                        for (OutputProduct outputProduct : outputProducts) {
                            if (product.getId().equals(outputProduct.getProductId())) {
                                overallPrice += product.getPrice() * outputProduct.getCount();
                            }
                        }
                    }
                    outputs.add(new Output(LocalDate.now().toString(), overallPrice, shopId, outputProducts));
                    System.out.println("sent successfully, total price is: " + overallPrice);
                    writeOutputs();

                    return;
                }
                case "2" -> {
                    List<OutputProduct> outputProduct = getOutputProduct(outputProducts);
                    if (outputProduct == null) continue;
                }
            }
        }


    }

    private static List<OutputProduct> getOutputProduct(List<OutputProduct> outputProducts) {

        products.stream()
                        .filter(product -> product.getCount()!=0)
                                .forEach(System.out::println);
        System.out.println("enter product id");
        String productId = strScanner.nextLine();
        Optional<Product> second = products.stream()
                .filter(s -> s.getId().equals(productId))
                .findFirst();
        if (second.isEmpty()) {
            System.out.println("wrong input");
            return null;
        }
        Product product = second.get();
        System.out.println("enter count");
        Integer count = intScanner.nextInt();

        intScanner.nextLine();
        if (count <= 0) {
            System.out.println("wrong input entered");
            return null;
        }
        if (product.getCount() < count) {
            System.out.println("we don't have this much product yet!");
            return null;
        }
        for (OutputProduct outputProduct : outputProducts) {
            if (outputProduct.getProductId().equals(productId)) {
                outputProduct.setCount(outputProduct.getCount() + count);
                product.setCount(product.getCount() - count);
                new ProductManagementService().writeProduct();
                return outputProducts;
            }
        }
        product.setCount(product.getCount() - count);
        new ProductManagementService().writeProduct();
        outputProducts.add(new OutputProduct(productId, count));
        System.out.println("product added");
        return outputProducts;
    }

    private void input() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 inputproduct
                    2 show inputs
                    """);
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    inputproduct();
                }
                case 2 -> {
                    showInputs();
                }
            }
        }
    }

    private void showInputs() {
        inputs.forEach(System.out::println);
    }

    private void inputproduct() {
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
        new ProductManagementService().writeProduct();
        System.out.println(selectedProduct.getCount());


        InputProduct inputProduct = new InputProduct(productId, block, countPerBlock);
        List<InputProduct> inputProductList = new ArrayList<>();
        inputProductList.add(inputProduct);
        String date = LocalDate.now().toString();

        Input input = new Input(date, w, companyId, inputProductList);
        inputs.add(input);
        writeinput();

        System.out.println("Yangi input muvaffaqiyatli qo‘shildi");
    }

    @SneakyThrows
    private void writeOutputs() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String json = gson.toJson(outputs);
            writer.write(json);
        } catch (Exception e) {
        }
    }

    @SneakyThrows

    public void readOutputs() {
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s).append("\n");
        }
        String json = sb.toString().trim();

        if (json.isEmpty()) {
            System.out.println("Outputs.json is empty");
            return;
        }

        Output[] outputArray = gson.fromJson(json, Output[].class);
        if (outputArray == null) {
            System.out.println("outputArray is null");
            return;
        }
        outputs = new ArrayList<>(Arrays.asList(outputArray));
    }
    @SneakyThrows
    public static void writeinput() {
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputfail));
        writer.write(gson.toJson(inputs));
        writer.close();
    }

    @SneakyThrows
    public void readinput() {
        BufferedReader reader = new BufferedReader(new FileReader(inputfail));
        String s = "";
        StringBuilder sb = new StringBuilder("");
        while ((s = reader.readLine()) != null) {
            sb.append(s).append("\n");
        }
        reader.close();
        Input[] userArray = gson.fromJson(sb.toString(), Input[].class);
        inputs = new ArrayList<>(Arrays.asList(userArray));
    }

}














package uz.app.service;

import uz.app.entity.Output;
import uz.app.entity.OutputProduct;
import uz.app.entity.Product;
import uz.app.entity.Shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            switch (intScanner.nextInt()){
                case 0->{return;}
                case 1->{}
                case 2->{outputService();}
                default -> System.out.println("Select desired section.");
            }
        }
    }

    private void outputService() {
        while (true){
            System.out.println("""
                    0 exit
                    1 send products to shop
                    2 show outputs
                    3 show available products
                    """);
            switch (strScanner.nextLine()){
                case "0"->{return;}
                case "1"->{sendProduct();}
                case "2"->{showOutputs();}
                case "3"->{showAvailableProducts();}
            }
        }
    }

    private void showAvailableProducts() {

    }

    private void showOutputs() {

    }

    private void sendProduct() {
        double overallPrice = 0.0;
        shops.forEach(System.out::println);
        System.out.println("enter shop id");
        String shopId = strScanner.nextLine();
        Optional<Shop> first = shops.stream()
                .filter(s -> s.getId().equals(shopId))
                .findFirst();
        if(first.isEmpty()){
            System.out.println("wrong input");
            return;
        }
        List<OutputProduct> outputProducts = getOutputProduct(new ArrayList<>());
        if (outputProducts==null) {
            return;
        }
        while (true){
            System.out.println("""
                    1 stop and send
                    2 continue with adding products
                    """);
            switch (strScanner.nextLine()){
                case "1"->{
                    for (Product product : products) {
                        for (OutputProduct outputProduct : outputProducts) {
                            if (product.getId().equals(outputProduct.getProductId())){
                                overallPrice+=product.getPrice()*outputProduct.getCount();
                            }
                        }
                    }
                    outputs.add(new Output(LocalDate.now(),overallPrice, shopId,outputProducts));
                    System.out.println("sent successfully, total price is: "+ overallPrice);
                    return;
                }
                case "2"->{
                    List<OutputProduct> outputProduct = getOutputProduct(outputProducts);
                    if (outputProduct==null) continue;
                }
            }
        }


    }

    private static List<OutputProduct> getOutputProduct(List<OutputProduct> outputProducts) {
        products.forEach(System.out::println);
        System.out.println("enter product id");
        String productId = strScanner.nextLine();
        Optional<Product> second = products.stream()
                .filter(s -> s.getId().equals(productId))
                .findFirst();
        if(second.isEmpty()){
            System.out.println("wrong input");
            return null;
        }
        System.out.println("enter count");
        Integer count = intScanner.nextInt();
        intScanner.nextLine();
        for (OutputProduct outputProduct : outputProducts) {
            if (outputProduct.getProductId().equals(productId)){
                outputProduct.setCount(outputProduct.getCount()+count);
                return outputProducts;
            }
        }
        outputProducts.add(new OutputProduct(productId,count));
        return outputProducts;
    }
}

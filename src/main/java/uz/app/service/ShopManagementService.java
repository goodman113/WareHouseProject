package uz.app.service;

import lombok.SneakyThrows;
import uz.app.entity.Shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static uz.app.db.DataSource.shops;
import static uz.app.service.InputOutputService.gson;
import static uz.app.utils.Utill.intScanner;
import static uz.app.utils.Utill.strScanner;

public class ShopManagementService {
    static String shopFile = "D:\\Programming\\G54 java\\java_modul_5\\WareHouseProject\\src\\main\\resources\\Shops.json";
    public void service() {
        while (true) {
            System.out.println("""
                    1. add shop
                    2. show shops
                    3. edit shop
                    4. delete shop
                    
                    0. Exit
                    """);
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    Shop shop = new Shop();
                    System.out.println("Enter shop name:");

                    shop.setName(strScanner.nextLine());
                    shops.add(shop);
                    writeShops();
                    System.out.println("Successfully added!");
                }
                case 2 -> {
                    shops.forEach(System.out::println);
                }
                case 3 -> {
                    shops.forEach(System.out::println);
                    System.out.println("Enter shopId:");
                    String shopId = strScanner.nextLine();
                    Shop shop = shops.stream()
                            .filter(o -> Objects.equals(shopId, o.getId()))
                            .findAny()
                            .orElse(null);
                    if (shop == null) {
                        System.out.println("Shop is not found!");
                        return;
                    }
                    System.out.println("Enter new name of shop:");
                    shop.setName(strScanner.nextLine());
                    writeShops();
                    System.out.println("Successfully edited!");

                }
                case 4 -> {
                    shops.forEach(System.out::println);
                    System.out.println("Enter shopId:");
                    String shopId = strScanner.nextLine();
                    Shop shop = shops.stream()
                            .filter(o -> Objects.equals(shopId, o.getId()))
                            .findAny()
                            .orElse(null);
                    if (shop == null) {
                        System.out.println("Shop is not found!");
                        return;
                    }
                    shops.remove(shop);
                    System.out.println("Successfully removed!");
                }
            }
        }
    }
    @SneakyThrows
    private void writeShops() {
        BufferedWriter writer = new BufferedWriter(new FileWriter(shopFile));
        writer.write(gson.toJson(shops));
        writer.close();
    }

    @SneakyThrows
    public void readShops() {
        BufferedReader reader = new BufferedReader(new FileReader(shopFile));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=reader.readLine())!=null){
            sb.append(s).append('\n');
        }
        reader.close();

        Shop[] shopsArray = gson.fromJson(sb.toString(), Shop[].class);
        if (shopsArray == null) {
            System.out.println("shopsArray is null");
            return;
        }
        shops = new ArrayList<>(Arrays.asList(shopsArray));
    }
}

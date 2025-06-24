package uz.app.service;

import uz.app.entity.Shop;

import java.util.Objects;

import static uz.app.db.DataSource.shops;
import static uz.app.utils.Utill.intScanner;
import static uz.app.utils.Utill.strScanner;

public class ShopManagementService {
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
}

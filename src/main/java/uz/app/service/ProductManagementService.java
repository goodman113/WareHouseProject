package uz.app.service;

import uz.app.entity.Category;
import uz.app.entity.Product;

import static uz.app.db.DataSource.*;
import static uz.app.utils.Utill.*;

public class ProductManagementService {
    public void service() {
        while (true) {
            if (products.isEmpty()) {
                System.out.println("There are no products.");
                return;
            }

            System.out.print("""
                    1. Show products
                    2. Add product
                    3. Edit product
                    4. Delete product
                    
                    0. Exit
                    >>>\s""");
            switch (intScanner.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    showProducts();
                }
                case 2 -> {
                    addProduct();
                }
                case 3 -> {
                    editProduct();
                }
                case 4 -> {
                    deleteProduct();
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }

    private void showProducts() {
        number.set(1);
        products.forEach(product -> System.out.printf("%s. %s\n", number.getAndIncrement(), product));
        System.out.println();
    }

    private void deleteProduct() {
        number.set(1);
        System.out.println("Products");
        products.forEach(product -> System.out.printf("%s. %s\n", number.getAndIncrement(), product.getName()));
        System.out.print("Select product to be deleted: ");
        products.remove(intScanner.nextInt() - 1);
        System.out.println("Product successfully deleted.");
    }

    private void editProduct() {
        while (true) {
            number.set(1);
            System.out.println("Products");
            products.forEach(product -> System.out.printf("%s. %s\n", number.getAndIncrement(), product.getName()));
            System.out.print("Select product: ");
            currentProduct = products.get(intScanner.nextInt() - 1);
            System.out.println();

            System.out.print("""
                    1. Edit product name
                    2. Edit price
                    3. Change category
                    4. Change measurement type
                    5. Change product manufacturer
                    
                    0. Exit
                    >>>\s""");

            switch (intScanner.nextInt()) {
                case 0 -> {
                    currentProduct = null;
                    return;
                }
                case 1 -> {
                    System.out.print("Enter product new name: ");
                    currentProduct.setName(strScanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter product new price: ");
                    currentProduct.setPrice(intScanner.nextDouble());
                }
                case 3 -> {
                    number.set(1);
                    System.out.println("Categories");
                    categories.forEach(category -> System.out.printf("%s. %s\n", number.getAndIncrement(), category.getName()));
                    System.out.printf("%s. Add new category\n", number.get());
                    System.out.print("Select product new category: ");
                    int i = intScanner.nextInt();
                    if (number.get() == i) {
                        Category category = new Category();
                        System.out.print("Enter category name: ");
                        category.setName(strScanner.nextLine());
                        categories.add(category);
                        currentProduct.setCategoryId(category.getId());
                    } else {
                        currentProduct.setCategoryId(categories.get(i - 1).getId());
                    }
                    System.out.println(currentProduct);
                }
                case 4 -> {
                    number.set(1);
                    System.out.println("Measures");
                    measures.forEach(measure -> System.out.printf("%s. %s\n", number.getAndIncrement(), measure.getName()));
                    System.out.print("Select product new measurement type: ");
                    currentProduct.setMeasure(measures.get(intScanner.nextInt() - 1));
                }
                case 5 -> {
                    number.set(1);
                    System.out.println("Companies");
                    companies.forEach(company -> System.out.printf("%s. %s\n", number.getAndIncrement(), company.getName()));
                    System.out.print("Select product new manufacturer: ");
                    currentProduct.setCompanyId(companies.get(intScanner.nextInt() - 1).getId());
                }
                default -> System.out.println("Select desired section.");
            }
        }
    }

    private void addProduct() {
        Product product = new Product();
        product.setCount(0);
        System.out.print("Enter product name: ");
        product.setName(strScanner.nextLine());
        System.out.print("Enter product price: ");
        product.setPrice(intScanner.nextDouble());
        number.set(1);
        System.out.println("Categories");
        categories.forEach(category -> System.out.printf("%s. %s\n", number.getAndIncrement(), category.getName()));
        System.out.printf("%s. Add new category\n", number.get());
        System.out.print("Select a product category: ");
        int i = intScanner.nextInt();
        if (number.get() == i) {
            Category category = new Category();
            System.out.print("Enter category name: ");
            category.setName(strScanner.nextLine());
            categories.add(category);
            product.setCategoryId(category.getId());
        } else {
            product.setCategoryId(categories.get(i - 1).getId());
        }
        number.set(1);
        System.out.println("Measures");
        measures.forEach(measure -> System.out.printf("%s. %s\n", number.getAndIncrement(), measure.getName()));
        System.out.print("Select a product measurement type: ");
        product.setMeasure(measures.get(intScanner.nextInt() - 1));
        number.set(1);
        System.out.println("Companies");
        companies.forEach(company -> System.out.printf("%s. %s\n", number.getAndIncrement(), company.getName()));
        System.out.print("Select a product manufacturer: ");
        product.setCompanyId(companies.get(intScanner.nextInt() - 1).getId());
        products.add(product);
        System.out.println("Product created successful.");
        System.out.println(product);
    }
}

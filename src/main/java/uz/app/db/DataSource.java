package uz.app.db;

import uz.app.entity.Company;
import uz.app.entity.*;
import uz.app.entity.enums.Measure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static uz.app.entity.enums.Measure.*;

public class DataSource {
    public static List<Company> companies = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Input> inputs = new ArrayList<>();
    public static List<Shop> shops = new ArrayList<>();
    public static List<Output> outputs = new ArrayList<>();
    public static List<Measure> measures = Arrays.asList(Measure.values());

    static {
        companies.add(new Company("Coca Cola", new ArrayList<>()));
        companies.add(new Company("Crafers", new ArrayList<>()));
        companies.add(new Company("Kamilka", new ArrayList<>()));

        categories.add(new Category("Drinks"));
        categories.add(new Category("Dairy products"));
        categories.add(new Category("Sweets"));
        categories.add(new Category("Meat"));

        products.add(new Product("Coca cola", 10000.0, 1000, categories.get(0).getId(), L, companies.get(0).getId()));
        products.add(new Product("Fanta", 10000.0, 1000, categories.get(0).getId(), L, companies.get(0).getId()));

        products.add(new Product("Candy", 40000.0, 1000, categories.get(2).getId(), KG, companies.get(1).getId()));
        products.add(new Product("Chocolate", 20000.0, 1000, categories.get(2).getId(), IMMEASURE, companies.get(1).getId()));

        products.add(new Product("Milk", 13000.0, 1000, categories.get(1).getId(), L, companies.get(2).getId()));
        products.add(new Product("Sour milk", 13000.0, 1000, categories.get(1).getId(), L, companies.get(2).getId()));
        products.add(new Product("Smetana", 25000.0, 1000, categories.get(1).getId(), L, companies.get(2).getId()));

        companies.get(0).setProducts(products.get(0));
        companies.get(0).setProducts(products.get(1));

        companies.get(1).setProducts(products.get(2));
        companies.get(1).setProducts(products.get(3));

        companies.get(2).setProducts(products.get(4));
        companies.get(2).setProducts(products.get(5));
        companies.get(2).setProducts(products.get(6));

        shops.add(new Shop("Korzinka"));
        shops.add(new Shop("Havas"));
        shops.add(new Shop("Makro"));



        InputProduct inputProduct = new InputProduct(products.get(0).getId(), 5, 6);
        InputProduct inputProduct1 = new InputProduct(products.get(1).getId(), 3, 6);
        List<InputProduct> inputProducts = new ArrayList<>();
        inputProducts.add(inputProduct1);
        inputProducts.add(inputProduct);

        OutputProduct outputProduct = new OutputProduct(products.get(0).getId(), 40);
        OutputProduct outputProduct1 = new OutputProduct(products.get(1).getId(), 30);
        List<OutputProduct> outputProducts = new ArrayList<>();
        outputProducts.add(outputProduct1);
        outputProducts.add(outputProduct);

        /// OverallPrice
        //(inputProducts.get(0).getBlock()*inputProducts.get(0).getCountPerBlock()*products.get(0).getPrice())+(inputProducts.get(1).getBlock()*inputProducts.get(1).getCountPerBlock()*products.get(1).getPrice())
        inputs.add(new Input(LocalDate.now(), 5 * 6 * 10000.0 + 3 * 6 * 10000.0, companies.get(0).getId(), inputProducts));

        outputs.add(new Output(LocalDate.now(), 40 * 10000.0 + 30 * 10000.0, shops.get(0).getId(), outputProducts));
    }
}










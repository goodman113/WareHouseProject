package uz.app.db;

import uz.app.entity.Company;
import uz.app.entity.*;
import uz.app.entity.enums.Measure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSource {
    public static List<Company> companies = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Input> inputs = new ArrayList<>();
    public static List<Output> outputs = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Shop> shops = new ArrayList<>();

    static {
        categories.add(new Category("Drinks"));
        categories.add(new Category("Milk dairy"));
        categories.add(new Category("Sweets"));
        categories.add(new Category("Meat"));

        companies.add(new Company("Coca Cola",new ArrayList<>()));
        companies.add(new Company("Crafers",new ArrayList<>()));
        companies.add(new Company("Kamilka",new ArrayList<>()));

        shops.add(new Shop("karzinka"));
        shops.add(new Shop("havas"));
        shops.add(new Shop("makro"));

        products.add(new Product("coca cola", 10000.0,1000,categories.get(0).getId(), Measure.L,companies.get(0).getId()));
        products.add(new Product("fanta", 10000.0,1000,categories.get(0).getId(), Measure.L,companies.get(0).getId()));

        products.add(new Product("candy", 40000.0,1000,categories.get(2).getId(), Measure.KG,companies.get(1).getId()));
        products.add(new Product("chocolate", 20000.0,1000,categories.get(2).getId(), null,companies.get(1).getId()));

        products.add(new Product("milk", 13000.0,1000,categories.get(1).getId(), Measure.L,companies.get(2).getId()));
        products.add(new Product("sour milk", 13000.0,1000,categories.get(1).getId(), Measure.L,companies.get(2).getId()));
        products.add(new Product("smetana", 25000.0,1000,categories.get(1).getId(), Measure.L,companies.get(2).getId()));

        companies.get(0).setProducts(products.get(0));
        companies.get(0).setProducts(products.get(1));
        companies.get(1).setProducts(products.get(2));
        companies.get(1).setProducts(products.get(3));
        companies.get(2).setProducts(products.get(4));
        companies.get(2).setProducts(products.get(5));
        companies.get(2).setProducts(products.get(6));

        InputProduct inputProduct = new InputProduct(products.get(0).getId(),5,6);
        InputProduct inputProduct1 = new InputProduct(products.get(1).getId(),3,6);
        List<InputProduct> inputProducts = new ArrayList<>();
        inputProducts.add(inputProduct1);
        inputProducts.add(inputProduct);

        OutputProduct outputProduct = new OutputProduct(products.get(0).getId(),40);
        OutputProduct outputProduct1 = new OutputProduct(products.get(1).getId(), 30);
        List<OutputProduct> outputProducts = new ArrayList<>();
        outputProducts.add(outputProduct1);
        outputProducts.add(outputProduct);

        /// OverallPrice
        //(inputProducts.get(0).getBlock()*inputProducts.get(0).getCountPerBlock()*products.get(0).getPrice())+(inputProducts.get(1).getBlock()*inputProducts.get(1).getCountPerBlock()*products.get(1).getPrice())
        inputs.add(new Input(LocalDate.now(),5*6*10000.0+3*6*10000.0,companies.get(0).getId(),inputProducts));

        outputs.add(new Output(LocalDate.now(),40*10000.0+30*10000.0,shops.get(0).getId(),outputProducts));
    }

}










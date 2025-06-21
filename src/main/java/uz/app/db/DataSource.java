package uz.app.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    static {

        categories.add(new Category("Drinks"));
        categories.add(new Category("Dairy products"));
        categories.add(new Category("Sweets"));
        categories.add(new Category("Meat"));

    }
}










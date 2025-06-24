package uz.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import uz.app.entity.Company;
import uz.app.entity.Input;
import uz.app.service.CompanyManagementService;
import uz.app.service.InputOutputService;
import uz.app.service.MainService;
import uz.app.service.ShopManagementService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import static uz.app.db.DataSource.*;
import static uz.app.utils.Utill.intScanner;

public class Main {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static String companyfail = "C:\\Users\\user\\IdeaProjects\\WareHouseProject/src/main/resources/Compaies.json";
    static String inputfail = "C:\\Users\\user\\IdeaProjects\\WareHouseProject/src/main/resources/Inputs.json";

    public static void main(String[] args) {
//        MainService mainService = new MainService();
//        while (true) {
//            try {
//                mainService.service();
//            } catch (Exception e) {
//                intScanner = new Scanner(System.in);
//                System.out.println("Select desired section.");
//            }
//        }
//        new InputOutputService().service();
//        new CompanyManagementService().service();
        new ShopManagementService().service();
    }

    @SneakyThrows
    public static void writecompany() {
        BufferedWriter writer = new BufferedWriter(new FileWriter(companyfail));
        writer.write(gson.toJson(companies));
        writer.close();
    }

    @SneakyThrows
    public static void readcompany() {
        BufferedReader reader = new BufferedReader(new FileReader(companyfail));
        String s = "";
        StringBuilder sb = new StringBuilder("");
        while ((s = reader.readLine()) != null) {
            sb.append(s).append("\n");
        }
        reader.close();
        Company[] userArray = gson.fromJson(sb.toString(), Company[].class);
        companies = new ArrayList<>(Arrays.asList(userArray));
    }


    @SneakyThrows
    public static void writeinput() {
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputfail));
        writer.write(gson.toJson(inputs));
        writer.close();
    }

    @SneakyThrows
    public static void readinput() {
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


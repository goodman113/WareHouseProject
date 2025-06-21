package uz.app.service;

import lombok.SneakyThrows;
import uz.app.entity.Company;
import uz.app.entity.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import static uz.app.db.DataSource.*;
import static uz.app.db.DataSource.gson;
import static uz.app.utils.Utill.*;

public class CompanyManagementService {
    static String companyFile = "D:\\Programming\\G54 java\\java_modul_5\\WareHouseProject\\src\\main\\resources\\Companies.json";
    public void service() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 show company
                    2 add company
                    3 edit company
                    4 delete company
                    5 show company's products
                    """);
            switch (intScanner.nextInt()) {
                case 0->{
                    return;
                }
                case 1->{
                    showcompany();
                }
                case 2->{
                    addcompany();
                }
                case 3->{
                    editcompany();
                }
                case 4->{
                    deletecompany();
                }
                case 5->{
                    showProduct();
                }
            }
        }
    }

    private void showProduct() {
        showcompany();
        System.out.println("enter company's id:");
        String companyId = strScanner.nextLine();
        Optional<Company> first = companies.stream()
                .filter(p -> p.getId().equals(companyId))
                .findFirst();
        if (first.isEmpty()){
            System.out.println("company with this id wasn't found");
            return;
        }
        products.stream()
                .filter(p-> companyId.equals(p.getCompanyId()))
                .forEach(System.out::println);

    }

    private void deletecompany() {
        showcompany();
        System.out.println("enter company id:");
        String companyId = strScanner.nextLine();

        Iterator<Company> iterator = companies.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Company company = iterator.next();
            if (Objects.equals(company.getId(), companyId)) {
                iterator.remove();
                System.out.println("company deleted successfully");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("company not found");
        }
    }



    private void editcompany() {
        showcompany();
        System.out.println("enter company id:");
        String companyId = strScanner.nextLine();
        boolean found = false;

        for (Company company : companies) {
            if (Objects.equals(companyId, company.getId())) {
                System.out.println("enter company name:");
                String name = strScanner.nextLine();
                company.setName(name);
                writeCompanies();
                found = true;
                System.out.println("company updated successfully");
                break;
            }
        }

        if (!found) {
            System.out.println("company not found");
        }
    }


    private void addcompany() {
        System.out.println("enter company name:");
        String name = strScanner.nextLine();
        Optional<Company> first = companies.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
        if (first.isPresent()){
            System.out.println("there is company with this name");
            return;
        }
        Company company = new Company();
        company.setName(name);
        companies.add(company);
        writeCompanies();


    }

    private void showcompany() {
        companies.forEach(company ->
                System.out.println("ID: " + company.getId() + ", Name: " + company.getName())
        );
    }

    @SneakyThrows
    private void writeCompanies() {
        BufferedWriter writer = new BufferedWriter(new FileWriter(companyFile));
        writer.write(gson.toJson(companies));
        writer.close();
    }

    @SneakyThrows
    public void readCompanies() {
        BufferedReader reader = new BufferedReader(new FileReader(companyFile));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=reader.readLine())!=null){
            sb.append(s).append('\n');
        }
        reader.close();

        Company[] companiesArray = gson.fromJson(sb.toString(), Company[].class);
        if (companiesArray == null) {
            System.out.println("companiesArray is null");
            return;
        }
        companies = new ArrayList<>(Arrays.asList(companiesArray));
    }
}

package uz.app.service;

import uz.app.entity.Company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import static uz.app.db.DataSource.companies;
import static uz.app.utils.Utill.*;

public class CompanyManagementService {
    public void service() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 show company
                    2 add company
                    3 edit company
                    4 delete company
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
            }
        }
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
        System.out.println("enetr company name:");
        String name = strScanner.nextLine();
        Company company = new Company();
        company.setName(name);
        companies.add(company);


    }

    private void showcompany() {
        companies.forEach(company ->
                System.out.println("ID: " + company.getId() + ", Name: " + company.getName())
        );
    }
}

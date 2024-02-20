package Facade;

import beans.Company;
import beans.Customer;

import java.util.List;

public class AdminFacadeTesterv4 {
    public static void main(String[] args) {
        try {
            // Creating an instance of AdminFacade
            AdminFacade adminFacade = new AdminFacade("admin@admin.com", "admin");

            // Logging in
            System.out.println("Logging in...");
            boolean loggedIn = adminFacade.login("admin@admin.com", "admin");
            if (!loggedIn) {
                System.out.println("Login failed. Exiting...");
                return;
            }

            System.out.println("Logged in successfully.");

            try {
                // Adding companies
                Company company = new Company("random", "Randomm@mail.com", "lastRandom");
                Company company3 = new Company("random2", "Rando22mm@mail.com", "la4stRandom");
                Company company4 = new Company("bob", "bobi@mail.com", "bobo32");
                Company company2 = new Company("dan", "dan@mail.com", "dani32");

                adminFacade.addCompany(company);
                adminFacade.addCompany(company3);
                adminFacade.addCompany(company4);
                adminFacade.addCompany(company2);

                System.out.println("Companies added successfully.");

                // Getting all companies
                List<Company> companies = adminFacade.getAllCompanies();
                if (!companies.isEmpty()) {
                    System.out.println("All Companies:");
                    for (Company c : companies) {
                        System.out.println(c);
                    }
                } else {
                    System.out.println("No companies found.");
                }

                // Adding customers
                Customer customer = new Customer("John ", "Cina", "johnc@mail.com");
                Customer customer2 = new Customer("David ", "Gur", "Dvd@mail.com");
                Customer customer3 = new Customer("Amnon ", "Pol", "Apol@mail.com");

                adminFacade.addCustomer(customer);
                adminFacade.addCustomer(customer2);
                adminFacade.addCustomer(customer3);

                System.out.println("Customers added successfully.");

                // Getting all customers
                List<Customer> customers = adminFacade.getAllCustomers();
                if (!customers.isEmpty()) {
                    System.out.println("All Customers:");
                    for (Customer c : customers) {
                        System.out.println(c);
                    }
                } else {
                    System.out.println("No customers found.");
                }

                // Deleting a company along with its associated coupons
                int companyIdToDelete = 1; // Replace with the ID of the company you want to delete
                adminFacade.deleteCompanyAndCoupons(companyIdToDelete);
                System.out.println("Company and associated coupons deleted successfully.");

            } catch (Exception e) {
                // Handle exceptions for each operation
                System.out.println("An error occurred: " + e.getMessage());
            }

        } catch (Exception e) {
            // Handle login exception
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

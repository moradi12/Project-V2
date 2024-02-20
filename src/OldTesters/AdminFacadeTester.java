package OldTesters;

import Facade.AdminFacade;
import beans.Company;
import beans.Customer;
import exception.*;

import java.util.List;

public class AdminFacadeTester {
    public static void main(String[] args) {
        try {
            // Creating an instance of AdminFacade
            AdminFacade adminFacade = new AdminFacade("admin@admin.com", "admin");

            // Logging in
            System.out.println("Logging in...");
            boolean loggedIn = adminFacade.login("admin@admin.com", "admin");
            if (loggedIn) {
                System.out.println("Logged in successfully.");
            } else {
                System.out.println("Login failed. Exiting...");
                return;
            }

            // Example usage of methods
            // Adding a company
            Company company = new Company("Hila Company", "Hila@mail.com", "try1234");
            adminFacade.addCompany(company);
            System.out.println("Company added successfully.");

            // Getting all companies
            List<Company> companies = adminFacade.getAllCompanies();
            if (!companies.isEmpty()) {
                System.out.println("All Companies: " + companies);
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
                int count = 1;
                for (Customer c : customers) {
                    System.out.println("Customer " + count++ + ":");
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
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

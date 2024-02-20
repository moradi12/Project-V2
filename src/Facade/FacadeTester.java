package Facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

import java.util.List;

public class FacadeTester {
    public static void main(String[] args) {
        // Test AdminFacade
        testAdminFacade();

        // Test CompanyFacade
        testCompanyFacade();

        // Test CustomerFacade
        testCustomerFacade();
    }

    private static void testAdminFacade() {
        try {
            AdminFacade adminFacade = new AdminFacade("admin@admin.com", "admin");

            // Add companies
            Company company1 = new Company("Random", "random@mail.com", "password1");
            adminFacade.addCompany(company1);
            Company company2 = new Company("Test", "test@mail.com", "password2");
            adminFacade.addCompany(company2);

            // Get all companies
            List<Company> companies = adminFacade.getAllCompanies();
            System.out.println("All Companies:");
            for (Company c : companies) {
                System.out.println(c);
            }

            // Add customers
            Customer customer1 = new Customer("John", "Doe", "john@mail.com");
            adminFacade.addCustomer(customer1);
            Customer customer2 = new Customer("Jane", "Smith", "jane@mail.com");
            adminFacade.addCustomer(customer2);

            // Get all customers
            List<Customer> customers = adminFacade.getAllCustomers();
            System.out.println("All Customers:");
            for (Customer c : customers) {
                System.out.println(c);
            }

            // Delete a company and its associated coupons
            int companyIdToDelete = 1; // Replace with the ID of the company you want to delete
            adminFacade.deleteCompanyAndCoupons(companyIdToDelete);
        } catch (Exception e) {
            System.out.println("An error occurred while testing AdminFacade: " + e.getMessage());
        }
    }

    private static void testCompanyFacade() {
        try {
            CompanyFacade companyFacade = new CompanyFacade("company@example.com", "password");

            // Constants for company and coupon IDs
            final int COMPANY_ID = 1;
            final int COUPON_ID_1 = 1;
            final int COUPON_ID_2 = 2;



            //ADD C
            //TODO ITS NOT WORKING I DONT KNOW WHY
            Coupon coupon1 = new Coupon(COUPON_ID_1, COMPANY_ID, Category.Food, "Coupon 1", "Description", java.sql.Date.valueOf("2024-01-01"), java.sql.Date.valueOf("2024-12-31"), 100, 10.99, "dd");
            companyFacade.addCoupon(coupon1);
            Coupon coupon2 = new Coupon(COUPON_ID_2, COMPANY_ID, Category.Hotels, "Coupon 2", "Description", java.sql.Date.valueOf("2024-01-01"), java.sql.Date.valueOf("2024-12-31"), 50, 15.50, "image.jpg");
            companyFacade.addCoupon(coupon2);

            // Get all coupons
            List<Coupon> coupons = companyFacade.getAllCoupons();
            System.out.println("All Coupons:");
            for (Coupon c : coupons) {
                System.out.println(c);
            }

            // Get company details
            String companyDetails = companyFacade.getCompanyDetails(COMPANY_ID);
            System.out.println("Company Details:");
            System.out.println(companyDetails);
        } catch (Exception e) {
            System.out.println("An error occurred while testing CompanyFacade: " + e.getMessage());
        }
    }

    private static void testCustomerFacade() {
        try {
            CustomerFacade customerFacade = new CustomerFacade("customer@example.com", "password");

            // Buy a coupon
            int couponIdToBuy = 1; // Replace with the ID of the coupon you want to buy
            customerFacade.buyCoupon(couponIdToBuy);

            // Get all coupons
            List<Coupon> coupons = customerFacade.getAllCoupons();
            System.out.println("All Coupons for Customer:");
            for (Coupon c : coupons) {
                System.out.println(c);
            }

            // Get customer details
            String customerDetails = customerFacade.getCustomerDetails();
            System.out.println("Customer Details:");
            System.out.println(customerDetails);
        } catch (Exception e) {
            System.out.println("An error occurred while testing CustomerFacade: " + e.getMessage());
        }
    }
}

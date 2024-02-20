import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Initializer.DatabaseInitializer;
import Initializer.DatabasePrinter;
import Initializer.InitializerCategory;
import Initializer.SampleDataInitializer;
import Sql.ConnectionPool;
import Sql.DBmanager;
import Sql.DButils;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import exception.CompanyCreationException;
import exception.CouponAlreadyExistsException;
import exception.CustomerAddException;
import exception.UserNotLogException;

import java.sql.Connection;
import java.sql.SQLException;

public class CouponSystemInitializer {
    public static void main(String[] args) {
        initializeCouponSystem();
    }

    public static void initializeCouponSystem() {
        try {
            // Create the 'couponnnn' database if it doesn't exist and use it
            DButils.runQuery("CREATE DATABASE IF NOT EXISTS couponnnn");
            DButils.runQuery("USE couponnnn");

            // Establish a connection from a connection pool to interact with the database
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();

            try {
                // Initialize database schema
                DatabaseInitializer.initialize(connection);
                // Add sample data
                SampleDataInitializer.initialize(connection);
                // Initialize categories
                InitializerCategory.initialize(connection);

                // Add companies and customers
                AdminFacade adminFacade = new AdminFacade(DBmanager.SQL_ADMIN_USERNAME, DBmanager.SQL_ADMIN_PASSWORD);
                addCompaniesAndCustomers(adminFacade);

                // Add coupons for a specific company
                CompanyFacade companyFacade = new CompanyFacade("company@example.com", "password");
                addCoupons(companyFacade);

                // Simulate customer actions
                CustomerFacade customerFacade = new CustomerFacade("customer@example.com", "password");
                simulateCustomerActions(customerFacade);

                // Print database contents
                DatabasePrinter.printDatabaseContents(connection);
            } finally {
                // Return the connection to the pool
                connectionPool.restoreConnection(connection);
            }
        } catch (SQLException | InterruptedException | CompanyCreationException | UserNotLogException |
                 CustomerAddException | CouponAlreadyExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addCompaniesAndCustomers(AdminFacade adminFacade) throws SQLException, CompanyCreationException, UserNotLogException, CustomerAddException {
        // Add companies
        Company company1 = new Company("Random", "random@mail.com", "password1");
        adminFacade.addCompany(company1);
        Company company2 = new Company("Test", "test@mail.com", "password2");
        adminFacade.addCompany(company2);

        // Add customers
        Customer customer1 = new Customer("John", "Doe", "john@mail.com");
        adminFacade.addCustomer(customer1);
        Customer customer2 = new Customer("Jane", "Smith", "jane@mail.com");
        adminFacade.addCustomer(customer2);
    }

    private static void addCoupons(CompanyFacade companyFacade) throws SQLException, CouponAlreadyExistsException {
        final int COMPANY_ID = 1; // Replace with the ID of the company
        Coupon coupon1 = new Coupon(1, COMPANY_ID, Category.Food, "Coupon 1", "Description", java.sql.Date.valueOf("2024-01-01"), java.sql.Date.valueOf("2024-12-31"), 100, 10.99, "dd");
        companyFacade.addCoupon(coupon1);
        Coupon coupon2 = new Coupon(2, COMPANY_ID, Category.Hotels, "Coupon 2", "Description", java.sql.Date.valueOf("2024-01-01"), java.sql.Date.valueOf("2024-12-31"), 50, 15.50, "image.jpg");
        companyFacade.addCoupon(coupon2);
    }

    private static void simulateCustomerActions(CustomerFacade customerFacade) throws SQLException, UserNotLogException {
        int couponIdToBuy = 1; // Replace with the ID of the coupon to buy
        customerFacade.buyCoupon(couponIdToBuy);
    }
}

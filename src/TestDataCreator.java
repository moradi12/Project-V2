import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Initializer.InitializerCategory;
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

public class TestDataCreator {
    public static void main(String[] args) {
        createTestData();
    }
    public static void createTestData() {
        try {
            // Create the database if it doesn't exist
            if (!DButils.createDatabase("database_couponnnn")) {
                System.out.println("Database creation failed.");
                return;
            }
            // Initialize the database connection pool and obtain a connection
            Connection connection = ConnectionPool.getInstance().getConnection();

            // Initialize categories
            InitializerCategory.initialize(connection);

            // Add companies and customers
            AdminFacade adminFacade = new AdminFacade(DBmanager.SQL_ADMIN_USERNAME, DBmanager.SQL_ADMIN_PASSWORD);
            addCompaniesAndCustomers(adminFacade);

            CompanyFacade companyFacade = new CompanyFacade("company@example.com", "password");
            addCoupons(companyFacade);

            CustomerFacade customerFacade = new CustomerFacade("customer@example.com", "password");
            simulateCustomerActions(customerFacade);

            connection.close();

            System.out.println("Test data created successfully.");
        } catch (SQLException | InterruptedException | CompanyCreationException | UserNotLogException |
                 CustomerAddException e) {
            System.out.println("An error occurred while creating test data: " + e.getMessage());
        } catch (CouponAlreadyExistsException e) {
            throw new RuntimeException(e);
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

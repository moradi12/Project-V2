package Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sql.SqlCommands.Customer_SQL;

public class CustomerInitializer {
    public static void initialize(Connection connection) throws SQLException {
        createCustomersTable(connection); // Ensure the customers table exists
        addCustomers(connection); // Add initial customers
    }

    // Create the customers table if it doesn't exist
    private static void createCustomersTable(Connection connection) throws SQLException {
        try (PreparedStatement createTableStatement = connection.prepareStatement(Customer_SQL.CREATE_TABLE_CUSTOMERS)) {
            createTableStatement.executeUpdate();
        }
    }

    // Add initial customers to the database
    private static void addCustomers(Connection connection) throws SQLException {
        addCustomer(connection, "John", "Doe", "john@eavi.com", "password123");
        addCustomer(connection, "Alice", "Smith", "alice@dora.com", "password456");
        addCustomer(connection, "Dobigal", "", "DOBIGAL@dobi.com", "320932gg");
    }

    // Add a single customer to the database
    private static void addCustomer(Connection connection, String firstName, String lastName, String email, String password) throws SQLException {
        String addCustomerSql = Customer_SQL.addCustomer;
        try (PreparedStatement addCustomerStatement = connection.prepareStatement(addCustomerSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            addCustomerStatement.setString(1, firstName);
            addCustomerStatement.setString(2, lastName);
            addCustomerStatement.setString(3, email);
            addCustomerStatement.setString(4, password);
            addCustomerStatement.executeUpdate();

            int customerId = retrieveGeneratedId(addCustomerStatement);
            if (customerId != -1) {
                System.out.println("New customer added with ID: " + customerId);
            } else {
                System.out.println("Failed to retrieve the generated ID for the new customer.");
            }
        }
    }

    // Retrieve the generated ID of the last inserted customer
    private static int retrieveGeneratedId(PreparedStatement statement) throws SQLException {
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1;
            }
        }
    }
}

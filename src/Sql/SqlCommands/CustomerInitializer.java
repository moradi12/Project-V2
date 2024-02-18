package Sql.SqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class initializes customers in the database
 */
public class CustomerInitializer {
    public static void initialize(Connection connection) throws SQLException {
        addCustomers(connection);
    }


    /**
     * Adds
     *
     * customers to the database
     * @param connection
     * @throws SQLException
     */


    // adding more than 1
    private static void addCustomers(Connection connection) throws SQLException {
        addCustomer(connection, "John Doe", "john@example.com", "password123");
        addCustomer(connection, "Alice Smith", "alice@example.com", "password456");

    }
    /// add a few at the same time
    
    private static void addCustomer(Connection connection, String name, String email, String password) throws SQLException {
        try (PreparedStatement addCustomerStatement = connection.prepareStatement(Customer_sql.addCustomer, Statement.RETURN_GENERATED_KEYS)) {
            addCustomerStatement.setString(1, name);
            addCustomerStatement.setString(2, email);
            addCustomerStatement.setString(3, password);
            addCustomerStatement.executeUpdate();

            try (ResultSet generatedKeys = addCustomerStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int customerId = generatedKeys.getInt(1);
                    System.out.println("New customer added with ID: " + customerId);
                } else {
                    System.out.println("Failed to retrieve the generated ID for the new customer.");
                }
            }
        }
    }
}
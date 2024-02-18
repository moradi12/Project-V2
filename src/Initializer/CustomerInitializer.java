package Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sql.SqlCommands.Customer_sql;

public class CustomerInitializer {
    public static void initialize(Connection connection) throws SQLException {
        addCustomers(connection);
    }

    private static void addCustomers(Connection connection) throws SQLException {
        addCustomer(connection, "John Doe", "john@eavi.com", "password123");
        addCustomer(connection, "Alice Smith", "alice@dora.com", "password456");
        addCustomer(connection, "Dobigal", "DOBIGAL@dobi.com", "320932gg");
    }

    private static void addCustomer(Connection connection, String name, String email, String password) throws SQLException {
        String addCustomerSql = Customer_sql.addCustomer;
        try (PreparedStatement addCustomerStatement = connection.prepareStatement(addCustomerSql)) {
            addCustomerStatement.setString(1, name);
            addCustomerStatement.setString(2, email);
            addCustomerStatement.setString(3, password);
            addCustomerStatement.executeUpdate();

            int customerId = retrieveGeneratedId(connection);
            if (customerId != -1) {
                System.out.println("New customer added with ID: " + customerId);
            } else {
                System.out.println("Failed to retrieve the generated ID for the new customer.");
            }
        }
    }

    private static int retrieveGeneratedId(Connection connection) throws SQLException {
        String getLastInsertIdSql = Customer_sql.getLastInsertId;
        try (PreparedStatement getLastInsertIdStatement = connection.prepareStatement(getLastInsertIdSql)) {
            try (ResultSet resultSet = getLastInsertIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return -1;
                }
            }}
    }}
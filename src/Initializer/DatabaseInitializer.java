package Initializer;

import Sql.SqlCommands.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class DatabaseInitializer {
    public static void initialize(Connection connection) throws SQLException {
        createTables(connection);
    }

    private static void createTables(Connection connection) throws SQLException {
        createTable(connection, Categories.CREATE_TABLE_CATEGORIES, "Categories table created successfully.");
        createTable(connection, companies.CREATE_TABLE_COMPANIES, "Companies table created successfully.");
        createTable(connection, coupons.CREATE_TABLE_COUPONS, "Coupons table created successfully.");
        createTable(connection, Customer_sql.CREATE_TABLE_CUSTOMERS, "Customers table created successfully.");
        createTable(connection, customers_vs_coupons.CREATE_TABLE_CVC, "Customers vs Coupons table created successfully.");
    }

    private static void createTable(Connection connection, String createTableQuery, String successMessage) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
            System.out.println(successMessage);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }
}

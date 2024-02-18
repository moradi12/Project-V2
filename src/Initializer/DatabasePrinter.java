package Initializer;

import Sql.SqlCommands.Categories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePrinter {
    public static void printDatabaseContents(Connection connection) throws SQLException {
        printCategories(connection);
        printCompanies(connection);
        printCoupons(connection);
        // Print other database contents as needed
    }

    private static void printCategories(Connection connection) throws SQLException {
        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_CATEGORIES), "Categories:");
    }

    private static void printCompanies(Connection connection) throws SQLException {
        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_CATEGORIES), "Companies:");
    }

    private static void printCoupons(Connection connection) throws SQLException {
        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_COUPONS), "Coupons:");
    }

    private static void printResultSet(ResultSet resultSet, String message) throws SQLException {
        System.out.println(message);
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("TITLE");
                String description = resultSet.getString("DESCRIPTION");
                // Print other coupon fields as needed
                System.out.println("ID: " + id + ", Title: " + title + ", Description: " + description);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
}
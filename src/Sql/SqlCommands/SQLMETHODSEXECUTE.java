package Sql.SqlCommands;

import Sql.DBmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLMETHODSEXECUTE {
    public static void main(String[] args) {
        createDatabaseAndTables();
    }

    public static void createDatabaseAndTables() {
        try (Connection conn = DBmanager.getConnection()) {
            createDatabase(conn); // Create database if it doesn't exist
            createTables(conn); // Create tables
        } catch (SQLException e) {
            // Handle the exception appropriately, e.g., log it or show an error message
            System.err.println("Error creating database and tables: " + e.getMessage());
        }
    }

    private static void createDatabase(Connection conn) throws SQLException {
        executeCommand(conn, "CREATE DATABASE IF NOT EXISTS couponnnn");
        executeCommand(conn, "USE couponnnn");
    }

    private static void createTables(Connection conn) throws SQLException {
        executeCommand(conn, Customer_SQL.CREATE_TABLE_CUSTOMERS);
        executeCommand(conn, Companies.CREATE_TABLE_COMPANIES);
        executeCommand(conn, Categories.CREATE_TABLE_CATEGORIES);
        executeCommand(conn, Coupons.CREATE_TABLE_COUPONS);
    }

    private static void executeCommand(Connection conn, String sqlCommand) throws SQLException {
        try (PreparedStatement stmt= conn.prepareStatement(sqlCommand)) {
            stmt.executeUpdate();
            System.out.println("Command executed successfully: " + sqlCommand);
        }
    }
}

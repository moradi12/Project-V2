package Initializer;

import Sql.SqlCommands.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableDropper {
    public static void dropTables(Connection connection) throws SQLException {
        dropTable(connection, Categories.CREATE_TABLE_CATEGORIES, "Categories table dropped successfully.");
        dropTable(connection, Companies.CREATE_TABLE_COMPANIES, "Companies table dropped successfully.");
        dropTable(connection, Coupons.CREATE_TABLE_COUPONS, "Coupons table dropped successfully.");
        dropTable(connection, Customer_SQL.CREATE_TABLE_CUSTOMERS, "Customers table dropped successfully.");
        dropTable(connection, Customers_VS_Coupons.CREATE_TABLE_CVC, "Customers vs Coupons table dropped successfully.");
    }

    private static void dropTable(Connection connection, String tableName, String message) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS " + tableName;
            statement.executeUpdate(sql);
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println("Error dropping table: " + e.getMessage());
            throw new RuntimeException("Error dropping table: " + e.getMessage(), e);
        }
    }
}

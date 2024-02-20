package Initializer;

import Sql.SqlCommands.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize(Connection connection) throws SQLException {
        createTables(connection);
    }


    /**createTables היא שיטה ברמה גבוהה יותר המאצילה את המשימה של יצירת טבלאות בודדות ל-createTable


     שהיא שיטה ברמה נמוכה יותר האחראית על ביצוע פקודות SQL
     וטיפול בחריגים. הפרדת הדאגות הזו הופכת את הקוד למודולרי יותר וקל יותר לתחזוקה

     .
     * initialize the tables
     * @param connection
     * @throws SQLException
     */
    /// create the tables
    private static void createTables(Connection connection) throws SQLException {
        createTable(connection, Categories.CREATE_TABLE_CATEGORIES, "Categories table created successfully.");
        createTable(connection, Companies.CREATE_TABLE_COMPANIES, "Companies table created successfully.");
        createTable(connection, Coupons.CREATE_TABLE_COUPONS, "Coupons table created successfully.");
        createTable(connection, Customer_SQL.CREATE_TABLE_CUSTOMERS, "Customers table created successfully.");
        createTable(connection, Customers_VS_Coupons.CREATE_TABLE_CVC, "Customers vs Coupons table created successfully.");
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
    // Method to drop a table

    public static void dropTable(Connection connection, String tableName) {
        String dropQuery = "DROP TABLE IF EXISTS couponnnn." + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropQuery);
            System.out.println("Table " + tableName + " dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Error dropping table " + tableName + ": " + e.getMessage());
        }
    }

}

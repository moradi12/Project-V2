package Initializer;

import Sql.SqlCommands.Categories;
import Sql.SqlCommands.Customer_SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePrinter {

    ///אחראית להדפסת התוכן של טבלאות שונות במסד הנתונים. זה קורא לשלוש שיטות נפרדות ////

    public static void printDatabaseContents(Connection connection) throws SQLException {
        printCategories(connection);
        printCompanies(connection);
        printCoupons(connection);

    }


    /**
     * Prints all categories in the database
     *
     * @param connection the Connection object for the database
     * @throws SQLException if a SQL exception occurs
     */
    private static void printCategories(Connection connection) throws SQLException {
        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_CATEGORIES), "Categories:");
    }


    /**
     * Prints all companies in the database.
     *
     * @param connection the Connection object for the database
     * @throws SQLException if a SQL exception occurs
     */
    private static void printCompanies(Connection connection) throws SQLException {
        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_CATEGORIES), "Companies:");
    }


    /**
     * Prints all coupons in the database.
     *
     * @param connection the Connection object for the database
     * @throws SQLException if a SQL exception occurs
     */
    private static void printCoupons(Connection connection) throws SQLException {
        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_COUPONS), "Coupons:");
    }

    private static void printCustomers(Connection connection)throws SQLException{
        printResultSet(connection.createStatement().executeQuery(Customer_SQL.getCustomer) , "Customer");

    }


    /**
     * Prints the contents of a ResultSet with a specified message.

     * @param resultSet the ResultSet containing the data to be printed
     * @param message   the message to be printed before the ResultSet contents
     * @throws SQLException if a SQL exception occurs
     */
    private static void printResultSet(ResultSet resultSet, String message) throws SQLException {
        System.out.println(message);
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("TITLE");
                String description = resultSet.getString("DESCRIPTION");
                System.out.println("ID: " + id + ", Title: " + title + ", Description: " + description);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
}
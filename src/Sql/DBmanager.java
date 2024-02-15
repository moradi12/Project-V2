// DBmanager class
package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBmanager {
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "12345678";

    // Database table names
    public static final String SQL_CUSTOMERS = "customers";
    public static final String SQL_CATEGORIES = "categories";
    public static final String SQL_COMPANIES = "companies";
    public static final String SQL_COUPONS = "coupons";
    public static final String SQL_CVC = "customers_vs_coupons";

    /**
     * @return a connection to the database.
     * @throws SQLException if a database access error occurs.
     */

    // establish a database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, SQL_USER, SQL_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error establishing database connection", e);
        }

    }




    /**
     * Closes the provided database connection.
     *
     * @param connection the connection to be closed.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}


package Initializer;

import Sql.ConnectionPool;
import Sql.DButils;

import java.sql.*;

public class Mainv3 {
    public static void main(String[] args) {
        try {
            /**
             * Create the 'couponnnn' database if it doesn't exist and use it.= */
            DButils.runQuery("CREATE DATABASE IF NOT EXISTS couponnnn");
            DButils.runQuery("USE couponnnn");



            /**
             * Establish a connection from a connection pool to interact with the database.
             */
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();

            try {
                // Initialize database schema
                DatabaseInitializer.initialize(connection);
                // Add sample data
                SampleDataInitializer.initialize(connection);
                // Initialize categories
                InitializerCategory.initialize(connection);


                // Print database contents
                DatabasePrinter.printDatabaseContents(connection);
            } finally {
                // Return the connection to the pool
                connectionPool.restoreConnection(connection);


                /// Category//
                InitializerCategory.initialize(connection);
            }
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

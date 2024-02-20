import Facade.ClientFacade;
import Initializer.DatabaseInitializer;
import Initializer.DatabasePrinter;
import Initializer.InitializerCategory;
import Initializer.SampleDataInitializer;
import LoginManagerSyst.LoginManager;
import Sql.ConnectionPool;
import Sql.DButils;
import beans.ClientType;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tester {
    public static void main(String[] args) {
        try {
            // Create the 'couponnnn' database if it doesn't exist and use it.
            DButils.runQuery("CREATE DATABASE IF NOT EXISTS couponnnn");
            DButils.runQuery("USE couponnnn");

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();

            try {
                DatabaseInitializer.initialize(connection);
                SampleDataInitializer.initialize(connection);
                InitializerCategory.initialize(connection);
                DatabasePrinter.printDatabaseContents(connection);

                // Login example
                loginExample();
            } finally {
                // Return the connection to the pool
                connectionPool.restoreConnection(connection);

                // Drop table if needed
                dropTable(connection, "name ");
            }
        } catch (SQLException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to drop a table
    public static void dropTable(Connection connection, String tableName) {
        String dropQuery = "DROP TABLE IF EXISTS " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropQuery);
            System.out.println("Table " + tableName + " dropped successfully.");
        } catch (SQLException e) {
            System.err.println("Error dropping table " + tableName + ": " + e.getMessage());
        }
    }

    // Example of logging in using the LoginManager class
    public static void loginExample() {
        LoginManager loginManager = LoginManager.getInstance();
        try {
            // Log in as a company
            ClientFacade companyFacade = loginManager.login("company@gmail.com", "password", ClientType.company);
            // Log out
            loginManager.logout(companyFacade);

            // Log in as a customer
            ClientFacade customerFacade = loginManager.login("customer@gmail.com.com", "password", ClientType.customer);
            // Log out
            loginManager.logout(customerFacade);

            // Log in as an administrator
            ClientFacade adminFacade = loginManager.login("admin@admin.com", "admin", ClientType.administrator);




            // Log out
            loginManager.logout(adminFacade);
        } catch (LoginException | SQLException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }



    //todo :





















}



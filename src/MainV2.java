//import Sql.ConnectionPool;
//import Sql.DBmanager;
//import Sql.DButils;
//import Sql.SqlCommands.*;
//
//import java.sql.*;
//
//public class MainV2 {
//    public static void main(String[] args) {
//        try {
//            // Create the 'couponnnn' database if it doesn't exist
//            DButils.runQuery("CREATE DATABASE IF NOT EXISTS couponnnn");
//            DButils.runQuery("USE couponnnn");
//
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            Connection connection = connectionPool.getConnection();
//
//            connection.setAutoCommit(false);
//            createTables(connection);
//            addSampleData(connection);
//            connection.commit();
//            System.out.println("Table creation and data insertion completed successfully.");
//            printCategories(connection);
//            printCompanies(connection);
//            printCompanyByEmailAndPassword(connection, "company@ffey.com", "password 48654");
//            printCompanyById(connection, 1);
//
//            // Return the connection to the pool
//            connectionPool.restoreConnection(connection);
//        } catch (SQLException | InterruptedException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    private static void createTables(Connection connection) throws SQLException {
//        createTable(connection, Categories.CREATE_TABLE_CATEGORIES, "Categories table created successfully.");
//        createTable(connection, companies.CREATE_TABLE_COMPANIES, "Companies table created successfully.");
//        createTable(connection, coupons.CREATE_TABLE_COUPONS, "Coupons table created successfully.");
//        createTable(connection, Customer_sql.CREATE_TABLE_CUSTOMERS, "Customers table created successfully.");
//        createTable(connection, customers_vs_coupons.CREATE_TABLE_CVC, "Customers vs Coupons table created successfully.");
//    }
//
//    private static void createTable(Connection connection, String createTableQuery, String successMessage) throws SQLException {
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(createTableQuery);
//            System.out.println(successMessage);
//        } catch (SQLException e) {
//            System.out.println("Error creating table: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    private static void addSampleData(Connection connection) throws SQLException {
//        addCategory(connection, "Enter a name", "Category Description");
//        addCompany(connection, "Company Name", "company@company.com", "password 48654");
//    }
//
//    private static void addCategory(Connection connection, String name, String description) throws SQLException {
//        PreparedStatement addCategoryStatement = connection.prepareStatement(Categories.ADD_CATEGORY);
//        addCategoryStatement.setString(1, name);
//        addCategoryStatement.setString(2, description);
//        addCategoryStatement.executeUpdate();
//    }
//
//    private static void addCompany(Connection connection, String name, String email, String password) throws SQLException {
//        PreparedStatement addCompanyStatement = connection.prepareStatement(companies.addCompany, Statement.RETURN_GENERATED_KEYS);
//        addCompanyStatement.setString(1, name);
//        addCompanyStatement.setString(2, email);
//        addCompanyStatement.setString(3, password);
//        addCompanyStatement.executeUpdate();
//
//        // Retrieve the generated ID
//        ResultSet generatedKeys = addCompanyStatement.getGeneratedKeys();
//        if (generatedKeys.next()) {
//            int companyId = generatedKeys.getInt(1);
//            System.out.println("New company added with ID: " + companyId);
//        } else {
//            System.out.println("Failed to retrieve the generated ID for the new company.");
//        }
//    }
//
//    private static void printCategories(Connection connection) throws SQLException {
//        printResultSet(connection.createStatement().executeQuery(Categories.GET_ALL_CATEGORIES), "Categories:");
//    }
//
//    private static void printCompanies(Connection connection) throws SQLException {
//        printResultSet(connection.createStatement().executeQuery(companies.GET_ALL_COMPANIES), "Companies:");
//    }
//
//    private static void printCompanyByEmailAndPassword(Connection connection, String email, String password) throws SQLException {
//        PreparedStatement getCompanyByEmailAndPasswordStatement = connection.prepareStatement(companies.getCompanyByEmailAndPassword);
//        getCompanyByEmailAndPasswordStatement.setString(1, email);
//        getCompanyByEmailAndPasswordStatement.setString(2, password);
//        printResultSet(getCompanyByEmailAndPasswordStatement.executeQuery(), "Company by Email and Password:");
//    }
//
//    private static void printCompanyById(Connection connection, int companyId) throws SQLException {
//        PreparedStatement getCompanyByIdStatement = connection.prepareStatement(companies.getCompanyById);
//        getCompanyByIdStatement.setInt(1, companyId);
//        printResultSet(getCompanyByIdStatement.executeQuery(), "Company by ID:");
//    }
//
//    private static void printResultSet(ResultSet resultSet, String message) throws SQLException {
//        System.out.println(message);
//        try {
//            while (resultSet.next()) {
//                int id = resultSet.getInt("ID");
//                String name = resultSet.getString("NAME");
//                String description = resultSet.getString("DESCRIPTION");
//                System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description);
//            }
//        } finally {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//        }
//    }
//}

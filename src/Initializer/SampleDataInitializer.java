package Initializer;

import Sql.SqlCommands.Categories;
import Sql.SqlCommands.Companies;

import java.sql.*;

public class SampleDataInitializer {


    /// להגדיר נתונים ראשוניים במסד הנתונים///

    /**
     * Initializes the database by adding a default category and company
     *
     * @param connection the database connection to use
     * @throws SQLException if a database access error occurs
     */
    public static void initialize(Connection connection) throws SQLException {
        addCategory(connection, "Enter a name", "Category Description");
        addCompany(connection, "Company Name", "company@company.com", "password 48654");
        // Add more sample data if needed
    }

    // הוספת קטגוריה חדשה למסד הנתונים///
    private static void addCategory(Connection connection, String name, String description) throws SQLException {
        try (PreparedStatement addCategoryStatement = connection.prepareStatement(Categories.ADD_CATEGORY)) {
            addCategoryStatement.setString(1, name);
            addCategoryStatement.setString(2, description);
            addCategoryStatement.executeUpdate();
        }
    }

    /**
     * Adds a new company to the database with the provided information
     *
     * @param connection a Connection object representing the database connection
     * @param name       the name of the company to be added
     * @param email      the email address of the company
     * @param password   the password associated with the company's account
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */


    private static void addCompany(Connection connection, String name, String email, String password) throws SQLException {
        //יחזיר את המפתחות שנוצרו אוטומטית //
        // כדי לדעת שהרשומות החדשות שהוכנסוה צריך לדעת את המפתחות שנוצרו לעיבוד נוסף כגון שיוך אותם לרשומות קשורות בטבלאות אחרות/
        try (PreparedStatement addCompanyStatement = connection.prepareStatement(Companies.addCompany, Statement.RETURN_GENERATED_KEYS)) {
            addCompanyStatement.setString(1, name);

            addCompanyStatement.setString(2, email);

            addCompanyStatement.setString(3, password);

            addCompanyStatement.executeUpdate();

            try (ResultSet generatedKeys = addCompanyStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int companyId = generatedKeys.getInt(1);
                    System.out.println("New company added with ID: " + companyId);

                } else {
                    System.out.println("Failed to retrieve the generated ID for the new company.");
                }
            }
        }
    }

    public static void deleteCompany(Connection connection, int companyId) throws SQLException {
        try (PreparedStatement deleteCompanyStatement = connection.prepareStatement(Companies.deleteCompany)) {
            deleteCompanyStatement.setInt(1, companyId);
            int rowsAffected = deleteCompanyStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Company deleted successfully.");
            } else {
                System.out.println("No company found with the given ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete company from the database", e);
        }
    }

}
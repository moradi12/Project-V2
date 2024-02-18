package Initializer;

import Sql.SqlCommands.Categories;
import Sql.SqlCommands.companies;

import java.sql.*;

class SampleDataInitializer {
    public static void initialize(Connection connection) throws SQLException {
        addCategory(connection, "Enter a name", "Category Description");
        addCompany(connection, "Company Name", "company@company.com", "password 48654");
        // Add more sample data if needed
    }

    private static void addCategory(Connection connection, String name, String description) throws SQLException {
        try (PreparedStatement addCategoryStatement = connection.prepareStatement(Categories.ADD_CATEGORY)) {
            addCategoryStatement.setString(1, name);
            addCategoryStatement.setString(2, description);
            addCategoryStatement.executeUpdate();
        }
    }

    private static void addCompany(Connection connection, String name, String email, String password) throws SQLException {
        try (PreparedStatement addCompanyStatement = connection.prepareStatement(companies.addCompany, Statement.RETURN_GENERATED_KEYS)) {
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
}

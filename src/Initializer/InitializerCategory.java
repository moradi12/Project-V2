package Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Sql.SqlCommands.Categories;
import beans.Category;

public class InitializerCategory {
    public static void initialize(Connection connection) throws SQLException {
        createCategoryTable(connection);
        addCategories(connection);
    }

    private static void createCategoryTable(Connection connection) throws SQLException {
        String createTableSql = Categories.CREATE_TABLE_CATEGORIES;
        try (PreparedStatement createTableStatement = connection.prepareStatement(createTableSql)) {
            createTableStatement.executeUpdate();
            System.out.println("Categories table created successfully.");
        }
    }

    private static void addCategories(Connection connection) throws SQLException {
        addCategory(connection, Category.Electricity, "Electricity category description");
        addCategory(connection, Category.Food, "Food category description");
        addCategory(connection, Category.Restaurant, "Restaurant category description");
        addCategory(connection, Category.Vacation, "Vacation category description");
        addCategory(connection, Category.SportProducts, "Sport products category description");
        addCategory(connection, Category.Hotels, "Hotels category description");
    }

    private static void addCategory(Connection connection, Category category, String description) throws SQLException {
        String addCategorySql = Categories.ADD_CATEGORY;
        try (PreparedStatement addCategoryStatement = connection.prepareStatement(addCategorySql)) {
            addCategoryStatement.setString(1, category.name());
            addCategoryStatement.setString(2, description);
            addCategoryStatement.executeUpdate();
            System.out.println("New category added: " + category.name());
        }
    }
}

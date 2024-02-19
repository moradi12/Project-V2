package Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sql.SqlCommands.Customer_SQL;

public class CustomerInitializer {
    public static void initialize(Connection connection) throws SQLException {
        addCustomers(connection);
    }
/* הוספת לקוחות מרובים למסד הנתונים.*/
    //עבור כל לקוח שיתווסף, ומעביר את פרטי //
    private static void addCustomers(Connection connection) throws SQLException {
        addCustomer(connection, "John Doe", "john@eavi.com", "password123");
        addCustomer(connection, "Alice Smith", "alice@dora.com", "password456");
        addCustomer(connection, "Dobigal", "DOBIGAL@dobi.com", "320932gg");
    }


    ///שיטה זו אחראית להוספת לקוח בודד למסד הנתונים
    // הוא לוקח את פרטי הלקוח (שם, מייל, סיסמה) כפרמטרים ומבצע פקודת SQL כדי להכניס את הלקוח למסד הנתונים
    // . בנוסף הוא מאחזר את המזהה שנוצר של הלקוח החדש שהוכנס ומדפיס הודעת הצלחה אם היא מצליחה/

    private static void addCustomer(Connection connection, String name, String email, String password) throws SQLException {
        String addCustomerSql = Customer_SQL.addCustomer;
        try (PreparedStatement addCustomerStatement = connection.prepareStatement(addCustomerSql)) {
            addCustomerStatement.setString(1, name);
            addCustomerStatement.setString(2, email);
            addCustomerStatement.setString(3, password);
            addCustomerStatement.executeUpdate();

            int customerId = retrieveGeneratedId(connection);
            if (customerId != -1) {
                System.out.println("New customer added with ID: " + customerId);
            } else {
                System.out.println("Failed to retrieve the generated ID for the new customer.");
            }
        }
    }
    //////זה מכין שאילתתSQL
    // כדי לשלוף את המזהה האחרון שהוכנס ממסד הנתונים.////
    /// אם היה חוזר 1
    ///מחזירה -1 כדי לציין שאחזור המזהה נכשל או שלא היו שורות בערכת התוצאות.
    //
    //שיטה זו מספקת דרך להשיג את המזהה של הרשומה האחרונה שהוכנסה במסד הנתונים,//
    private static int retrieveGeneratedId(Connection connection) throws SQLException {
        String getLastInsertIdSql = Customer_SQL.getLastInsertId;
        try (PreparedStatement getLastInsertIdStatement = connection.prepareStatement(getLastInsertIdSql)) {
            try (ResultSet resultSet = getLastInsertIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return -1;
                }
            }}
    }}
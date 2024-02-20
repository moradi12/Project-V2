package Initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sql.SqlCommands.Coupons;

// A friendly initializer for coupons
public class CouponInitializer {

    // Method to initialize coupons
    public static void initialize(Connection connection) throws SQLException {
        addCoupons(connection);
        addCoupons(connection);
        addCoupons(connection);

    }
    //todo its not working !@#!@#@!#!@#
    private static void addCoupons(Connection connection) throws SQLException {
        addCoupon(connection, "10OFF", 10);
        addCoupon(connection, "20OFF", 20);
        addCoupon(connection, "FREESHIP", 100); // Free shipping for a delightful surprise!
    }

    // Method to add a coupon
    private static void addCoupon(Connection connection, String code, int discount) throws SQLException {
        String addCouponSql = Coupons.addCoupon;
        try (PreparedStatement addCouponStatement = connection.prepareStatement(addCouponSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Set coupon details
            addCouponStatement.setString(1, code);
            addCouponStatement.setInt(2, discount);
            addCouponStatement.executeUpdate();

            // Retrieve the generated ID
            int couponId = retrieveGeneratedId(addCouponStatement);
            if (couponId != -1) {
                System.out.println("New coupon added with ID: " + couponId);
            } else {
                System.out.println("Failed to retrieve the generated ID for the new coupon.");
            }
        }
    }

    // Method to retrieve generated ID
    private static int retrieveGeneratedId(PreparedStatement statement) throws SQLException {
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1;
            }
        }
    }
}

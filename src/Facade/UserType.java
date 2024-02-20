package Facade;

import Sql.DButils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserType {
    private String email;
    private String password;
    private boolean isLogged;

    public UserType(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Add validation for email format if needed
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        this.isLogged = logged;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }

    private boolean existsInTable(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName +
                " WHERE email = ? AND password = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, getEmail());
        params.put(2, getPassword());
        ResultSet resultSet = Sql.DButils.runQueryForResult(sql, params);
        try {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error closing result set: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean loginAsCompany() {
        return existsInTable(Sql.DBmanager.SQL_COMPANIES);
    }

    public boolean loginAsCustomer() {
        return existsInTable(Sql.DBmanager.SQL_CUSTOMERS);
    }

    public boolean loginAsCategoryUser() {
        return existsInTable(Sql.DBmanager.SQL_CATEGORIES);
    }

    public boolean loginAsCouponUser() {
        return existsInTable(Sql.DBmanager.SQL_COUPONS);
    }

    public boolean loginAsCustomerVsCouponUser() {
        return existsInTable(Sql.DBmanager.SQL_CVC);
    }

}

//package Facade;
//
//import Sql.DButils;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class UserType {
//    private String email;
//    private String password;
//    private boolean isLogged;
//
//    public UserType(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        // Add validation for email format if needed
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isLogged() {
//        return isLogged;
//    }
//
//    public void setLogged(boolean logged) {
//        this.isLogged = logged;
//    }
//
//    @Override
//    public String toString() {
//        return "UserType{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", isLogged=" + isLogged +
//                '}';
//    }
//
//    private boolean isCompany() {
//        String sql = "SELECT COUNT(*) FROM " + Sql.DBmanager.SQL_COMPANIES +
//                " WHERE email = ? AND password = ?";
//        Map<Integer, Object> params = new HashMap<>();
//        params.put(1, getEmail());
//        params.put(2, getPassword());
//        ResultSet resultSet = Sql.DButils.runQueryForResult(sql, params);
//        try {
//            if (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                return count > 0;
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query: " + e.getMessage());
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//            } catch (SQLException e) {
//                System.out.println("Error closing result set: " + e.getMessage());
//            }
//        }
//        return false;
//    }
//
//    public boolean login() {
//        if (isCompany()) {
//            System.out.println("Logged in as company.");
//            return true;
//        }
//
//        String sql = "SELECT COUNT(*) FROM " + Sql.DBmanager.SQL_CUSTOMERS +
//                " WHERE email = ? AND password = ?";
//        Map<Integer, Object> params = new HashMap<>();
//        params.put(1, getEmail());
//        params.put(2, getPassword());
//        ResultSet resultSet = Sql.DButils.runQueryForResult(sql, params);
//        try {
//            if (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                if (count > 0) {
//                    System.out.println("Logged in as customer.");
//                    return true;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query: " + e.getMessage());
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//            } catch (SQLException e) {
//                System.out.println("Error closing result set: " + e.getMessage());
//            }
//        }
//
//        System.out.println("Login failed: Invalid credentials.");
//        return false;
//    }
//}

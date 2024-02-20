package DBDAO;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultSetUtils {

    /**  * A class containing methods to map ResultSet objects to Java beans */
    /*** Maps a ResultSet to a Company object
     * @param resultSet The ResultSet containing company data
     * @return The mapped Company object
     */


    /**
     * Maps a ResultSet to a Company object
     *
     * @param resultSet The ResultSet containing company data
     * @return The mapped Company object*/
    public static Company mapResultSetToCompany(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("idCOMPANIES");
        String name = resultSet.getString("NAME");
        String email = resultSet.getString("EMAIL");
        String password = resultSet.getString("PASSWORD");
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        List<Coupon> list = couponsDBDAO.getAllCouponsByCompany(id);
        return new Company(id, name, email, password, list);
    }

    /**
     * Maps a ResultSet to a Coupon object
     ** @param resultSet The ResultSet containing coupon data
     * @return The mapped Coupon object
     */


    public static Coupon mapResultSetToCoupon(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int companyId = resultSet.getInt("companyId");
        Category category = Category.valueOf(resultSet.getString("category"));
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        Date startDate = resultSet.getDate("startDate");
        Date endDate = resultSet.getDate("endDate");
        Integer amount = resultSet.getInt("amount");
        Double price = resultSet.getDouble("price");
        String image = resultSet.getString("image");

        return new Coupon(id, companyId, category, title, description, (java.sql.Date) startDate, (java.sql.Date) endDate, amount, price, image);
    }


    /**
     * Maps a ResultSet to a Customer object
     *
     * @param resultSet The ResultSet containing customer data
     * @return The mapped Customer object
     */


    public static Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        List<Coupon> coupons = new ArrayList<>();
        return new Customer(id, firstName, lastName, email, password, coupons);
    }
}








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

    //Company//

    public static Company mapResultSetToCompany(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("idCOMPANIES");
        String name = resultSet.getString("NAME");
        String email = resultSet.getString("EMAIL");
        String password = resultSet.getString("PASSWORD");
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        List<Coupon> list = couponsDBDAO.getAllCouponsByCompany(id);
        return new Company(id, name, email, password, list);
    }


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

        return new Coupon(id, companyId, category, title, description, startDate, endDate, amount, price, image);
    }

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








package DBDAO;

import DAO.CouponsDAO;
import Sql.DButils;
import Sql.SqlCommands.coupons;
import beans.Category;
import beans.Coupon;
import exception.DatabaseQueryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDAO {

    @Override
    public void addCoupon(Coupon coupon) {
        String sql = Sql.SqlCommands.coupons.addCoupon;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, coupon.getCategory().ordinal() + 1);
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, (coupon.getStartDate().getTime()));
        params.put(6, coupon.getAmount());
        params.put(7, coupon.getPrice());
        params.put(8, coupon.getImage());

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupon added successfully.");
        } else {
            System.out.println("Failed to add coupon.");
        }

    }

    @Override
    public void updateCoupon(Coupon coupon) {
        String sql = coupons.updateCoupon;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getTitle());
        params.put(2, coupon.getDescription());
        params.put(3, coupon.getAmount());
        params.put(4, coupon.getPrice());
        params.put(5, coupon.getId());

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupon updated successfully.");
        } else {
            System.out.println("Failed to update coupon.");
        }
    }


    @Override
    public void deleteCoupon(int couponID, int companyID) {
        String sql = coupons.deleteCoupon;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        params.put(2, companyID);
        boolean success = DButils.runQuery(sql, params);
        if (success) {
            System.out.println("Coupon deleted successfully.");
        } else {
            System.out.println("Failed to delete coupon.");
        }
    }


    @Override
    public List<Coupon> getAllCoupons() {


        return null;
    }

    @Override
    public void getOneCoupon(int couponID) {
        String sql = Sql.SqlCommands.coupons.getOneCoupon;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, params);
            if (resultSet.next()) {
                Coupon coupon = ResultSetUtils.mapResultSetToCoupon(resultSet);
                System.out.println(coupon);
            } else {
                System.out.println("Coupon with ID " + couponID + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while retrieving coupon with ID " + couponID, e);
        }
    }


    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {
        String sql = Sql.SqlCommands.coupons.deleteCouponPurchase;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupon purchase deleted successfully.");
        } else {
            System.out.println("Failed to delete coupon purchase.");
        }
    }

    @Override
    public void deleteCouponPurchaseHistory(int couponID) {
        String sql = Sql.SqlCommands.coupons.deleteCouponPurchaseHistory;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupon purchase history deleted successfully.");
        } else {
            System.out.println("Failed to delete coupon purchase history.");
        }
    }

    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = Sql.SqlCommands.coupons.getAllCouponsByCompany;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, params);
            while (resultSet.next()) {
                Coupon coupon = ResultSetUtils.mapResultSetToCoupon(resultSet);
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while retrieving coupons by company", e);
        }
        return coupons;
    }


    @Override
    public List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = Sql.SqlCommands.coupons.getAllCouponsByCategoryAndCompany;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, category.ordinal() + 1);
        params.put(2, companyId);

        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, params);
            while (resultSet.next()) {
                Coupon coupon = ResultSetUtils.mapResultSetToCoupon(resultSet);
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while retrieving coupons by category and company", e);
        }

        return coupons;
    }

    @Override
    public List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = Sql.SqlCommands.coupons.getAllCouponsUpToPriceAndCompany;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, price);
        params.put(2, companyId);

        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, params);
            while (resultSet.next()) {
                Coupon coupon = ResultSetUtils.mapResultSetToCoupon(resultSet);
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while retrieving coupons up to price and company", e);
        }

        return coupons;
    }

}

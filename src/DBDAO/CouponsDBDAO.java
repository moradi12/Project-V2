package DBDAO;

import DAO.CouponsDAO;
import Sql.DButils;
import Sql.SqlCommands.Coupons;
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


    /**
     * Implementation of the methods from dao
     */
    @Override
    public void addCoupon(Coupon coupon) {
        String sql = Coupons.addCoupon;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, coupon.getCategory().ordinal() + 1);
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, (coupon.getStartDate().getTime()));
        params.put(6, (coupon.getEndDate().getTime()));
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupon added successfully.");
        } else {
            System.out.println("Failed to add coupon.");
        }

    }


    @Override
    public void updateCoupon(Coupon coupon) {
        String sql = Coupons.updateCoupon;
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
    public void deleteCoupon(int couponID) {
        String sql = Coupons.deleteCoupon;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        boolean success = DButils.runQuery(sql, params);
        if (success) {
            System.out.println("Coupon deleted successfully.");
        } else {
            System.out.println("Failed to delete coupon.");
        }
    }


    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = Coupons.getAllCoupons;

        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, new HashMap<>());
            while (resultSet.next()) {
                Coupon coupon = ResultSetUtils.mapResultSetToCoupon(resultSet);
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while retrieving all coupons", e);
        }

        return coupons;
    }

    @Override
    public void getOneCoupon(int couponID) {
        String sql = Coupons.getOneCoupon;
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
        String sql = Coupons.addCouponPurchase;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupon purchase added successfully.");
        } else {
            System.out.println("Failed to add coupon purchase.");
        }
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {
        String sql = Coupons.deleteCouponPurchase;
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
    public List<Coupon> getExpiredCoupons(LocalDate currentDate) {
        List<Coupon> expiredCoupons = new ArrayList<>();
        String sql = Coupons.getExpiredCoupons;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, currentDate);

        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, params);
            while (resultSet.next()) {
                Coupon coupon = ResultSetUtils.mapResultSetToCoupon(resultSet);
                expiredCoupons.add(coupon);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while retrieving expired coupons", e);
        }

        return expiredCoupons;
    }

    @Override
    public void deleteCouponsByCompany(int companyId) {
        String sql = Coupons.deleteCouponsByCompany;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Coupons associated with company deleted successfully.");
        } else {
            System.out.println("Failed to delete coupons associated with company.");
        }
    }




    @Override
    public void deleteCouponPurchaseHistory(int couponID) {
        String sql = Coupons.deleteCouponPurchaseHistory;
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
        String sql = Coupons.getAllCouponsByCompany;
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
        String sql = Coupons.getAllCouponsByCategoryAndCompany;
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
    public List<Coupon> getAllCouponsByMaxPrice(double MaxPrice, int companyId) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = Coupons.getAllCouponsUpToPriceAndCompany;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, MaxPrice);
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

    @Override
    public boolean isCouponExists(int couponID) {
        String sql = Coupons.getCouponById;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        try {
            ResultSet resultSet = DButils.runQueryForResult(sql, params);
            return resultSet.next();
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while checking if coupon exists", e);
        }
    }

    @Override
    public void deleteExpiredCoupons(int companyID, LocalDate currentDate) {
        String sql = Coupons.deleteExpiredCoupons;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, currentDate);

        boolean success = DButils.runQuery(sql, params);

        if (success) {
            System.out.println("Expired coupons deleted successfully for company with ID: " + companyID);
        } else {
            System.out.println("Failed to delete expired coupons for company with ID: " + companyID);
        }
    }}



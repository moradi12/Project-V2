package DBDAO;

import DAO.CouponsDAO;
import Sql.DButils;
import beans.Category;
import beans.Coupon;

import java.time.LocalDate;
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
        /// what to do ?
        //        params.put(5, new java.sql.Date(coupon.getStartDate().getTime()));
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

    }

    @Override
    public void deleteCoupon(int couponID, int companyID) {

    }

    @Override
    public List<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public void getOneCoupon(int couponID) {

    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchaseHistory(int couponID) {

    }

    @Override
    public List<Coupon> getAllCouponsByCompany(int companyId) {
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId) {
        return null;
    }

    @Override
    public List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId) {
        return null;
    }

    @Override
    public List<Coupon> getExpiredCoupons(LocalDate currentDate) {
        return null;
    }
}

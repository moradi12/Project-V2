package DAO;

import beans.Category;
import beans.Coupon;

import java.time.LocalDate;
import java.util.List;

public interface CouponsDAO {
    void addCoupon(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(int couponID);

    List<Coupon> getAllCoupons();

    void getOneCoupon(int couponID);

    void addCouponPurchase(int customerID, int couponID);

    void deleteCouponPurchase(int customerID, int couponID);

    List<Coupon> getExpiredCoupons(LocalDate currentDate);
    void deleteCouponsByCompany(int companyId);

    void deleteCouponPurchaseHistory(int couponID);

    List<Coupon> getAllCouponsByCompany(int companyId);

    List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId);

    List<Coupon> getAllCouponsByMaxPrice(double price, int companyId);

    boolean isCouponExists(int couponID);

    //List<Coupon> getExpiredCoupons(LocalDate currentDate);
    void deleteExpiredCoupons(int companyID, LocalDate currentDate);


}


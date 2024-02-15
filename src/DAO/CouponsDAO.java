package DAO;

import beans.Category;
import beans.Coupon;

import java.time.LocalDate;
import java.util.List;

public interface CouponsDAO {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponID,int companyID);
    List<Coupon> getAllCoupons();

    void getOneCoupon(int couponID);
    void addCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchase(int customerID, int couponID);
    void deleteCouponPurchaseHistory(int couponID);

    List<Coupon> getAllCouponsByCompany(int companyId);

    List<Coupon> getAllCouponsByCategoryAndCompany(Category category, int companyId);

    List<Coupon> getAllCouponsUpToPriceAndCompany(double price, int companyId);


    //List<Coupon> getExpiredCoupons(LocalDate currentDate);






}


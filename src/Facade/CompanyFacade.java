package Facade;

import beans.Coupon;
import java.util.List;

public class CompanyFacade extends Facade.ClientFacade {
    public CompanyFacade(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        // Implement login logic here
        return false;
    }

    public void addCoupon(Coupon coupon) {
    }

    public void updateCoupon(Coupon coupon) {
    }

    public void deleteCoupon(int couponID) {
    }

    public List<Coupon> getAllCoupons() {
        return null;
    }

    public List<Coupon> getAllCouponsByCategory() {
        return null;
    }

    public List<Coupon> getAllCouponsByMaxPrice(double MaxPrice) {
        return null;
    }

    public String returnCompanyDetails() {
        return null;
    }
}
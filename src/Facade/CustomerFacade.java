package Facade;

import beans.Category;
import beans.Coupon;

import java.util.List;

public class CustomerFacade extends Facade.ClientFacade {
    public CustomerFacade(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    public void buyCoupon(int couponID) {
    }

    public List<Coupon> getAllCoupons() {
        return null;
    }

    public List<Coupon> getAllCouponsByCategory(Category category) {
        return null;
    }

    public List<Coupon> getAllCouponsByUpToPrice(double price) {
        return null;
    }

    public String getCustomerDetails() {
        return null;
    }
}

package Facade;
import DBDAO.CustomersDBDAO;
import DBDAO.CouponsDBDAO;
import beans.Category;
import beans.Coupon;
import java.util.List;

public class CustomerFacade extends ClientFacade {

    private final CouponsDBDAO couponsDBDAO;

    public CustomerFacade(String email, String password) {
        super(email, password);
        this.couponsDBDAO = new CouponsDBDAO();
    }

    @Override
    public boolean login(String email, String password) {
        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        return customersDBDAO.isCustomerExists(email, password);
    }

    public void buyCoupon(int couponID) {
        // Implement logic to buy a coupon
    }

    public List<Coupon> getAllCoupons() {
        return couponsDBDAO.getAllCoupons();
    }

    public List<Coupon> getAllCouponsByCategory(Category category) {

        return null;
    }

    public List<Coupon> getAllCouponsByUpToPrice(double price) {
        return null;
    }

    public String getCustomerDetails() {
        // Implement logic to get customer details from the database
        return "Customer Details";
    }
}

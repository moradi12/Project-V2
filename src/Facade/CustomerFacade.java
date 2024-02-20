package Facade;

import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import beans.Category;
import beans.Coupon;
import beans.Customer;
import exception.UserNotLogException;

import java.sql.SQLException;
import java.util.List;

public class CustomerFacade extends ClientFacade {

    private final CouponsDBDAO couponsDBDAO;
    private final CustomersDBDAO customersDBDAO;
    private Customer customer;

    public CustomerFacade(String email, String password) {
        super(email, password);
        this.couponsDBDAO = new CouponsDBDAO();
        this.customersDBDAO = new CustomersDBDAO();
    }
    @Override
    public boolean login(String email, String password) {
        if (customersDBDAO.isCustomerExists(email, password)) {
            customer = customersDBDAO.getOneCustomer(Integer.parseInt(email));
            System.out.println("Login successful for email: " + email);
            return true;
        } else {
            System.out.println("Login failed for email: " + email);
            return false;
        }
    }

    public void buyCoupon(int couponID) throws SQLException, UserNotLogException {
        if (isLogged()) {
            couponsDBDAO.addCouponPurchase(customer.getId(), couponID);
        } else {
            throw new UserNotLogException("Customer not logged in.");
        }
    }

    public List<Coupon> getAllCoupons() throws SQLException, UserNotLogException {
        if (isLogged()) {
            return couponsDBDAO.getAllCoupons();
        } else {
            throw new UserNotLogException("Customer not logged in.");
        }
    }

    public List<Coupon> getAllCouponsByCategory(Category category) throws UserNotLogException {
        if (isLogged()) {
            return couponsDBDAO.getAllCouponsByCategoryAndCompany(category, customer.getId());
        } else {
            throw new UserNotLogException("Customer not logged in.");
        }
    }

    public List<Coupon> getAllCouponsByUpToPrice(double price) throws SQLException, UserNotLogException {
        if (isLogged()) {
            return couponsDBDAO.getAllCouponsByMaxPrice(price, customer.getId());
        } else {
            throw new UserNotLogException("Customer not logged in.");
        }
    }

    public String getCustomerDetails() throws UserNotLogException {
        if (isLogged()) {
            return customer.toString();
        } else {
            throw new UserNotLogException("Customer not logged in.");
        }

    }
}
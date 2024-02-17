package Facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import exception.CompanyNotFoundException;
import exception.CouponAlreadyExistsException;
import exception.CouponNotFoundException;
import exception.DatabaseQueryException;

import java.util.List;

public class CompanyFacade extends ClientFacade {
    private CompaniesDBDAO companiesDBDAO;
    private CouponsDBDAO couponsDBDAO;

    public CompanyFacade(String email, String password) {
        super(email, password);
        this.companiesDBDAO = new CompaniesDBDAO();
        this.couponsDBDAO = new CouponsDBDAO();
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    public void addCoupon(Coupon coupon) throws CouponAlreadyExistsException {
        try {
            if (couponsDBDAO.isCouponExists(coupon.getId())) {
                throw new CouponAlreadyExistsException("Coupon with ID " + coupon.getId() + " already exists.");
            }
            couponsDBDAO.addCoupon(coupon);
        } catch (DatabaseQueryException e) {
            throw new CouponAlreadyExistsException("Failed to add coupon: " + e.getMessage());
        }
    }

    public void updateCoupon(Coupon coupon) throws CouponNotFoundException {
        try {
            if (!couponsDBDAO.isCouponExists(coupon.getId())) {
                throw new CouponNotFoundException("Coupon with ID " + coupon.getId() + " not found.");
            }
            couponsDBDAO.updateCoupon(coupon);
        } catch (DatabaseQueryException e) {
            throw new CouponNotFoundException("Failed to update coupon: " + e.getMessage());
        }
    }

    public void deleteCoupon(int couponID) throws CouponNotFoundException {
        try {
            if (!couponsDBDAO.isCouponExists(couponID)) {
                throw new CouponNotFoundException("Coupon with ID " + couponID + " not found.");
            }
            couponsDBDAO.deleteCoupon(couponID);
        } catch (DatabaseQueryException e) {
            throw new CouponNotFoundException("Failed to delete coupon: " + e.getMessage());
        }
    }

    public List<Coupon> getAllCoupons() throws CouponNotFoundException {
        try {
            return couponsDBDAO.getAllCoupons();
        } catch (DatabaseQueryException e) {
            throw new CouponNotFoundException("Failed to retrieve coupons: " + e.getMessage());
        }
    }

    public List<Coupon> getAllCouponsByCategory(String category, int companyId) throws CouponNotFoundException {
        try {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            return couponsDBDAO.getAllCouponsByCategoryAndCompany(categoryEnum, companyId);
        } catch (IllegalArgumentException e) {
            throw new CouponNotFoundException("Invalid category: " + category);
        } catch (DatabaseQueryException e) {
            throw new CouponNotFoundException("Failed to retrieve coupons by category: " + e.getMessage());
        }
    }

    public List<Coupon> getAllCouponsByMaxPrice(double MaxPrice, int companyId) throws CouponNotFoundException {
        try {
            return couponsDBDAO.getAllCouponsByMaxPrice(MaxPrice, companyId);
        } catch (DatabaseQueryException e) {
            throw new CouponNotFoundException("Failed to retrieve coupons by price: " + e.getMessage());
        }
    }// todo finish the try!!!!!!!!!!!!!

    public String getCompanyDetails(int companyId) throws CompanyNotFoundException {
        try {
            Company company = companiesDBDAO.getOneCompany(companyId);
            if (company != null) {
                return company.toString();
            } else {
                throw new CompanyNotFoundException("Company with ID " + companyId + " not found.");
            }
        } catch (DatabaseQueryException e) {
            throw new CompanyNotFoundException("Failed to retrieve company details: " + e.getMessage());
        }

    }
}
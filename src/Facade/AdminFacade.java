package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import beans.Company;
import beans.Customer;
import exception.*;

import java.util.List;

public class AdminFacade extends ClientFacade {

    private CompaniesDBDAO companiesDBDAO;
    private CustomersDBDAO customersDBDAO;
    private CouponsDBDAO couponsDBDAO;

    public AdminFacade(String email, String password) {
        super(email, password);
        this.companiesDBDAO = new CompaniesDBDAO();
        this.customersDBDAO = new CustomersDBDAO();
        this.couponsDBDAO = new CouponsDBDAO();
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addCompany(Company company) throws CompanyCreationException, UserNotLogException {
        if (!isLogged()) {
            throw new UserNotLogException("User is not logged in.");
        }

        try {
            if (companiesDBDAO.isCompanyExists(company.getEmail(), company.getPassword())) {
                System.out.println("Company already exists.");
            } else {
                companiesDBDAO.addCompany(company);
                System.out.println("Company added successfully.");
            }
        } catch (Exception e) {
            throw new CompanyCreationException("Failed to add company: " + e.getMessage());
        }
    }

    public void updateCompany(Company company) throws UserNotLogException, CompanyUpdateException {
        if (!isLogged()) throw new UserNotLogException("User is not logged in.");
        try {
            companiesDBDAO.updateCompany(company);
            System.out.println("Company updated successfully.");
        } catch (Exception e) {
            throw new CompanyUpdateException("Failed to update company: " + e.getMessage());
        }
    }

    public void deleteCompanyAndCoupons(int companyId) throws UserNotLogException {
        if (!isLogged()) throw new UserNotLogException("User is not logged in.");

        // Delete all coupons associated with the company
        try {
            couponsDBDAO.deleteCouponsByCompany(companyId);
            System.out.println("Coupons associated with company deleted successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete coupons associated with company: " + e.getMessage());
        }

        // Delete the company
        try {
            companiesDBDAO.deleteCompany(companyId);
            System.out.println("Company deleted successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete company: " + e.getMessage());
        }
    }

    public List<Company> getAllCompanies() throws UserNotLogException {
        if (!isLogged()) throw new UserNotLogException("User is not logged in.");
        try {
            return companiesDBDAO.getAllCompanies();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve companies: " + e.getMessage());
        }
    }

    public Company getOneCompany(int companyId) throws CompanyNotFoundException {
        Company company = companiesDBDAO.getOneCompany(companyId);
        if (company == null) throw new CompanyNotFoundException("Company with ID " + companyId + " not found.");
        return company;
    }

    public void addCustomer(Customer customer) throws CustomerAddException, UserNotLogException {
        if (!isLogged()) {
            throw new UserNotLogException("User is not logged in.");
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            customersDBDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            customersDBDAO.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.");
        } catch (Exception e) {
            System.out.println("Failed to delete customer: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() throws DatabaseQueryException, UserNotLogException {
        if (!isLogged()) throw new UserNotLogException("User is not logged in.");
        try {
            return customersDBDAO.getAllCustomers();
        } catch (Exception e) {
            throw new DatabaseQueryException("Failed to retrieve all customers: " + e.getMessage(), e);
        }
    }

    public Customer getOneCustomer(int customerId) throws CustomerNotFoundException {
        try {
            Customer customer = customersDBDAO.getOneCustomer(customerId);
            if (customer == null) throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
            return customer;
        } catch (Exception e) {
            throw new CustomerNotFoundException("Failed to retrieve customer: " + e.getMessage());
        }
    }
}

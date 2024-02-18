package Facade;


import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Sql.DBmanager;
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
    }
    /**
     * Constructs an AdminFacade instance with the specified email and password.
     *
     * Checks if the user is an admin by verifying email and password  */
    @Override
    public boolean login(String email, String password) {
        Facade.UserType userType = new Facade.UserType(email, password);
        if (userType.getEmail().equals(DBmanager.SQL_USER) && userType.getPassword().equals(DBmanager.SQL_PASSWORD)) {
            System.out.println("Logged in as admin.");
            return true;
        } else {
            System.out.println("Login failed: Invalid credentials.");
            return false;
        }
    }
    /**
     * Adds a new company to the system.
     */

    public void addCompany(Company company) throws CompanyCreationException {
        if (isLogged()) {
            try {
                companiesDBDAO.addCompany(company);
                System.out.println("Company added successfully.");
            } catch (Exception e) {
                throw new CompanyCreationException("Failed to add company: " + e.getMessage());
            }
        } else {
            throw new CompanyCreationException("User is not logged in.");
        }
    }
    /**
     * Updates an existing company in the system
     * */
    public void updateCompany(Company company) throws UserNotLogException, CompanyUpdateException {
        if (isLogged()) {
            try {
                companiesDBDAO.updateCompany(company);
                System.out.println("Company updated successfully.");
            } catch (Exception e) {
                throw new CompanyUpdateException("Failed to update company: " + e.getMessage());
            }
        } else {
            throw new UserNotLogException("User is not logged in.");
        }
    }


    /**
     * Deletes a company from the system
     */
    public void deleteCompany(int companyId) throws UserNotLogException {
        if (isLogged()) {
            try {
                companiesDBDAO.deleteCompany(companyId);
                System.out.println("Company deleted successfully.");
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete company: " + e.getMessage());
            }
        } else {
            throw new UserNotLogException("User is not logged in.");
        }
    }
// todo finish the try!!!!!!!!!!!!!

    /** List of company
     */
    public List<Company> getAllCompanies() throws UserNotLogException {
        if (isLogged()) {
            try {
                return companiesDBDAO.getAllCompanies();
            } catch (Exception e) {
                throw new RuntimeException("Failed to retrieve companies: " + e.getMessage());
            }
        } else {
            throw new UserNotLogException("User is not logged in.");
        }
    }

    /**
     * Get one company from the system
     */
    public Company getOneCompany(int companyId) throws CompanyNotFoundException {
        Company company = companiesDBDAO.getOneCompany(companyId);
        if (company == null) {
            throw new CompanyNotFoundException("Company with ID " + companyId + " not found.");
        }
        return company;
    }

    /**
     * Adds a new company to the system.
     *
     * @param customer add a customer
     * @throws CompanyCreationException if company creation fails
     */
    public void addCustomer(Customer customer) throws CustomerAddException, UserNotLogException {
        if (isLogged()) {
            try {
                customersDBDAO.addCustomer(customer);
                System.out.println("Customer added successfully.");
            } catch (Exception e) {
                throw new CustomerAddException("Failed to add customer: " + e.getMessage());
            }
        } else {
            throw new UserNotLogException("User is not logged in.");
        }
    }
    /**
     * Updates an existing customer in the system
     */
    public void updateCustomer(Customer customer) {
        try {
            customersDBDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    /**
     * Deletes a customer from the system
     */
    public void deleteCustomer(int customerId) {
        try {
            customersDBDAO.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.");
        } catch (Exception e) {
            System.out.println("Failed to delete customer: " + e.getMessage());
        }
    }

    /**
     * get a list of customer from the system
     */

    public List<Customer> getAllCustomers() throws DatabaseQueryException {
        try {
            return customersDBDAO.getAllCustomers();
        } catch (Exception e) {
            throw new DatabaseQueryException("Failed to retrieve all customers: " + e.getMessage(), e);
        }
    }
    /**
     * get one customer from the system by ID
     */
    public Customer getOneCustomer(int customerId) {
        try {
            Customer customer = customersDBDAO.getOneCustomer(customerId);
            if (customer == null) {
                System.out.println("Customer with ID " + customerId + " not found.");
            }
            return customer;
        } catch (Exception e) {
            System.out.println("Failed to retrieve customer: " + e.getMessage());
            return null;
        }
    }
}


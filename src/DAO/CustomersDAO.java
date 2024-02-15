package DAO;

import beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomersDAO {

    boolean isCustomerExists(String email, String password);

    void addCustomer(Customer customer);
    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerId) ;
    List<Customer> getAllCustomers() ;
    Customer getOneCustomer(int customerID) ;


}
package DBDAO;

import DAO.CustomersDAO;
import Sql.DButils;
import Sql.SqlCommands.Customer_sql;
import beans.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Sql.SqlCommands.Customer_sql.updateCustomer;

public class CustomersDBDAO implements CustomersDAO {
    @Override
    public boolean isCustomerExists(String email, String password) {
        return false;
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = Customer_sql.addCustomer;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());

        boolean success = DButils.runQuery(sql, params);
        if (success) {
            System.out.println("Customer added successfully!");
        } else {
            System.out.println("Failed to add customer.");
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        params.put(5, customer.getId());
        if (!DButils.runQuery(Customer_sql.updateCustomer, params)) {
            throw new SQLException("Failed to update customer.");
        }
        System.out.println("Customer updated successfully!");
    }


    @Override
    public void deleteCustomer(int customerId) {
        String sql = Customer_sql.deleteCustomer;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);

        boolean success = DButils.runQuery(sql, params);
        if (success) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Failed to delete customer.");
        }
    }


    @Override
    public List<Customer> getAllCustomers() {
        String sql = Customer_sql.getAllCustomers;
        ResultSet resultSet = DButils.runQueryForResult(sql, new HashMap<>());

        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
        }
        return customers;
        }


    @Override
    public Customer getOneCustomer(int customerId) {
        String sql = Customer_sql.getAllCustomers;
        Map<Integer, Object> params = new HashMap<>();

        ResultSet resultSet = DButils.runQueryForResult(sql, params);

        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
        }
        return (Customer) customers;
    }
}

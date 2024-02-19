package DBDAO;

import DAO.CustomersDAO;
import Sql.DButils;
import Sql.SqlCommands.Customer_SQL;
import beans.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {

    /**
     * Implementation of the CustomersDAO
     */
    @Override
    public boolean isCustomerExists(String email, String password) {
        String sql = Customer_SQL.getCustomerByEmail;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);

        try (ResultSet resultSet = DButils.runQueryForResult(sql, params)) {
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("PASSWORD");
                return storedPassword.equals(password);
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error checking if customer exists: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = Customer_SQL.addCustomer;
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
        if (!DButils.runQuery(Customer_SQL.updateCustomer, params)) {
            throw new SQLException("Failed to update customer.");
        }
        System.out.println("Customer updated successfully!");
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = Customer_SQL.deleteCustomer;
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
        String sql = Customer_SQL.getAllCustomers;
        List<Customer> customers = new ArrayList<>();

        try (ResultSet resultSet = DButils.runQueryForResult(sql, new HashMap<>())) {
            while (resultSet.next()) {
                customers.add(ResultSetUtils.mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer getOneCustomer(int customerId) {
        String sql = Customer_SQL.getCustomer;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        try (ResultSet resultSet = DButils.runQueryForResult(sql, params)) {
            if (resultSet.next()) {
                return ResultSetUtils.mapResultSetToCustomer(resultSet);
            } else {
                System.out.println("Customer with ID " + customerId + " not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
            return null;
        }
    }
}

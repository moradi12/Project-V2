package DBDAO;

import beans.Customer;
import java.util.List;

public class CustomerResult {
    private int status; // 1 for success, 0 for failure
    private List<Customer> customers;

    public CustomerResult(int status, List<Customer> customers) {
        this.status = status;
        this.customers = customers;
    }

    public int getStatus() {
        return status;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}

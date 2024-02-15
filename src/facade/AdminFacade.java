package facade;

import Facade.ClientFacade;
import beans.Company;
import beans.Customer;
import java.util.List;

public class AdminFacade extends ClientFacade {
    public AdminFacade(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
    public void addCompany(Company company){
    }

    public void updateCompany(Company company){
    }

    public void deleteCompany(int companyId){

    }

    public List<Company> getAllCompanies(){
    return  null;
    }

    public Company getOneCompany(int companyId){
    return  null;
    }

    public void addCustomer(Customer customer){

    }

    public void updateCustomer(Customer customer){
    }


    public void deleteCustomer(int customerId){
    }

    public List<Customer> getAllCustomers() {
        return null;
    }

}

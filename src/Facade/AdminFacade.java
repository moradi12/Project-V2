package Facade;

import Sql.DBmanager;
import beans.Company;
import beans.Customer;
import exception.InvalidCredentials;
import lombok.SneakyThrows;

import java.util.List;

public class AdminFacade extends ClientFacade {



    public AdminFacade(String email, String password) {
        super(email, password);
    }


    @Override
    public boolean login(String email, String password) {
        UserType userType = new UserType(email, password);
        if (userType.getEmail().equals(DBmanager.SQL_USER) && userType.getPassword().equals(DBmanager.SQL_PASSWORD)) {
            System.out.println("Logged in as admin.");
            return true;
        } else {
            System.out.println("Login failed: Invalid credentials.");
            return false;
        }
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

    public Customer getOneCustomer(int customerID){

        return null;
    }

}
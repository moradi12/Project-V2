package Facade;



import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CustomersDAO customersDAO;
    protected CompaniesDAO companiesDAO;
    protected CouponsDAO couponsDAO;
    protected boolean isLogged;

    /**
     * Constructor for ClientFacade with email and password.
     * @param email The email of the client.
     * @param password The password of the client.
     */


    public ClientFacade(String email, String password) {
        customersDAO = new CustomersDBDAO();
        companiesDAO = new CompaniesDBDAO();
        couponsDAO = new CouponsDBDAO();
        setLogged(login(email, password));
            if (isLogged()) {
                loginSuccessMessage();
            } else {
                System.out.println("Login failed: Invalid credentials.");
            }

    }
    /**
     * Abstract method for login functionality.
     *
     * @param email    The email of the client.
     * @param password The password of the client.
     * @return A Customer object upon successful login.
     * @return True if login is successful, false otherwise.
     */
    public abstract boolean login(String email, String password);
    protected boolean isLogged() {
        return isLogged;
    }
    protected void setLogged(boolean logged) {
        isLogged = logged;
    }
    public void logout() {
        setLogged(false);;
        System.out.println("Logged out.");
    }
    protected void loginSuccessMessage() {
        System.out.println("Logged in successfully.");
    }

}

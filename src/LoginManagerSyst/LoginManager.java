package LoginManagerSyst;

import Facade.ClientFacade;
import beans.ClientType;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import exception.AdminException;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;

/**
 * The LoginManager class provides methods for logging in and logging out users of different client types
 */


public class LoginManager {
    private static volatile LoginManager instance = null;

    private LoginManager() {}


    /**
     * singleton instance of LoginManager
     */
    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null)
                    instance = new LoginManager();
            }
        }
        return instance;
    }
    /**
     * Logs in a user with the provided email, password, and client type
     * @param email - The email of the user
     * @param password - The password of the user
     * @param clientType - The type of the client (company, customer, administrator)
     * @return The facade corresponding to the logged-in user
     * Exceptions + Costume Exception :
     * @throws LoginException  If the login fails
     * @throws SQLException    If a database access error occurs
     * @throws IllegalArgumentException If email or password is null, or clientType is invalid
     */


    public ClientFacade login(String email, String password, ClientType clientType) throws LoginException, SQLException, IllegalArgumentException {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }

        ClientFacade facade = null;
        try {
            switch (clientType) {
                case company:
                    facade = new CompanyFacade(email, password);
                    break;
                case customer:
                    facade = new CustomerFacade(email, password);
                    break;
                case administrator:
                    if (!email.equals("admin@admin.com") || !password.equals("admin")) {
                        throw new AdminException("Invalid email or password for admin");
                    }
                    facade = new AdminFacade(email, password);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid client type: " + clientType);
            }
        } catch (AdminException e) {
            System.out.println(e.getMessage());
            throw new LoginException("Login failed: " + e.getMessage());
        }

        if (facade == null) {
            System.out.println("Failed to create facade for client: " + clientType);
        } else {
            System.out.println("Login successful for client: " + clientType);
        }
        return facade;
    }
    /**
     * Logs out a user.
     */
    public void logout(ClientFacade facade) {
        facade.logout();
        System.out.println("Logout successful");
    }
}

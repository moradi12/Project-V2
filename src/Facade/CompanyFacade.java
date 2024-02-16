package Facade;

public class CompanyFacade extends ClientFacade{
    /**
     * Constructor for ClientFacade with email and password.
     *
     * @param email    The email of the client.
     * @param password The password of the client.
     */
    public CompanyFacade(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}

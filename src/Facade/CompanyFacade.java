package Facade;
public class CompanyFacade extends Facade.ClientFacade {
    public CompanyFacade(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}

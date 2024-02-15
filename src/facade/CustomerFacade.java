package facade;
public class CustomerFacade extends Facade.ClientFacade {
    public CustomerFacade(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}

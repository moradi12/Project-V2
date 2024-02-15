package exception;

public class CustomerIsNotAdminException extends Exception {
    public CustomerIsNotAdminException(String message) {
        super(message + " You're not admin ");
    }
}

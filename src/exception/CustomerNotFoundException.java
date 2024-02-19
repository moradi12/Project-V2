package exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
        System.out.println("Customer not found exception occurred: " + message);
    }
}

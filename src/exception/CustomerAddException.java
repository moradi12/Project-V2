package exception;

public class CustomerAddException extends Exception {
    public CustomerAddException(String message) {
        super(message);
        System.out.println("Customer add exception occurred: " + message);
    }
}

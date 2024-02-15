package exception;

public class AdminException extends Exception {
    public AdminException(String message) {
        super(message);
        System.out.println("AdminException occurred with message: " + message);    }
}

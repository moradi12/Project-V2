package exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
        System.out.println("Invalid credentials exception occurred: " + message);

    }

}

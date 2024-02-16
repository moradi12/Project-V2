package exception;

public class InvalidCredentials extends Exception {
    public InvalidCredentials(String message) {
        super(message);
        System.out.println("Invalid credentials exception occurred: " + message);
    }
}

package exception;

public class UserNotLogException extends Exception {
    public UserNotLogException(String message) {
        super(message + " You're not logged in.");
    }
}

package exception;

public class DatabaseQueryException extends RuntimeException {
    public DatabaseQueryException(String message, Throwable cause) {
        super(message, cause);
        System.out.println("Database query exception occurred: " + message);

    }
}


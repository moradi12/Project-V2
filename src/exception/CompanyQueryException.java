package exception;

public class CompanyQueryException extends RuntimeException {
    public CompanyQueryException(String message) {
        super(message);
    }

    public CompanyQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}

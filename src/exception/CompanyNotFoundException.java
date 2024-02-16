package exception;

public class CompanyNotFoundException extends Exception {
    public CompanyNotFoundException(String message) {
        super(message);
        System.out.println("Company not found exception occurred: " + message);
    }
}
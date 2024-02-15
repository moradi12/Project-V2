package exception;

public class CouponAlreadyExistsException extends Exception{
    public CouponAlreadyExistsException(String message) {
        super(message +" CouponAlreadyExists!!!");
    }
}

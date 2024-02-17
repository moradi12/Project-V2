//import DAO.CouponsDAO;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.RequiredArgsConstructor;
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@RequiredArgsConstructor
//public class CouponExpirationDailyJob implements Runnable {
//
//    private final CouponsDAO couponsDAO;
//    private boolean quit;
//
//    @Override
//    public void run() {
//        while (!quit) {
//            needToDeleteCoupons(LocalDate.now()); // Pass current date
//
//            try {
//                Thread.sleep(24 * 60 * 60 * 1000);
//            } catch (InterruptedException e) {
//                // Handle the InterruptedException gracefully
//                Thread.currentThread().interrupt(); // Restore interrupted status
//                System.out.println("Thread interrupted while sleeping: " + e.getMessage());
//            }
//        }
//    }
//
//    public void stop() {
//        quit = true;
//    }
//
//
//    private void needToDeleteCoupons(LocalDate currentDate) {
//
//    }
//}
//

import DBDAO.CouponsDBDAO;
import beans.Coupon;

import java.time.LocalDate;
import java.util.List;

/**
 * A class representing a daily job for deleting expired coupons from the database.
 * The job runs on a separate thread and deletes expired coupons every 24 hours.
 */
public class CouponExpirationDailyJob implements Runnable {
    private final CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    private volatile boolean quit;
    private final long TIME = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

    /**
     * The main method that starts the daily job
     * It creates a new thread for the job
     * starts it, sleeps for 24 hours
     * and then stops the job
     */
    public static void main(String[] args) {
        CouponExpirationDailyJob dailyJob = new CouponExpirationDailyJob();
        Thread dailyJobThread = new Thread(

                ///Thread dailyJobThread = new Thread(dailyJob);/////
                String.valueOf(dailyJob));
        dailyJobThread.start();

        try {
            Thread.sleep(dailyJob.TIME); // Sleep for 24 hours
        } catch (InterruptedException e) {}

        dailyJob.stop();
    }

    /**
     * Constructs a CouponExpirationDailyJob object

     * By default, the job is not set to quit
     */
    public CouponExpirationDailyJob() {
        this.quit = false;
    }

    /**
     * The run method of the daily job
     * It runs continuously until quit is set to true, deleting expired coupons every 24 hours
     */
    @Override
    public void run() {
        while (!quit) {
            deleteExpiredCoupons();
            try {
                Thread.sleep(TIME); // Sleep for 24 hours
            } catch (InterruptedException e) {
                quit = true;
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Stops the daily job
     * Sets the quit flag to true, causing the job to terminate
     */
    public void stop() {
        quit = true;
    }
    /**
     * Deletes expired coupons from the database
     * Retrieves the list of expired coupons for the current date
     * deletes each expired coupon and prints a message for each deletion
     */
    private void deleteExpiredCoupons() {
        List<Coupon> expiredCoupons = couponsDBDAO.getExpiredCoupons(LocalDate.now());
        expiredCoupons.forEach(coupon -> {
            couponsDBDAO.deleteCoupon(coupon.getId());
            System.out.println("Expired coupon (ID: " + coupon.getId() + ") has been removed.");
        }
///    public static final String getExpiredCouponsQuery = "SELECT * FROM coupons WHERE expiration_date <= ? ";/////
        );
    }
}

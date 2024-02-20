package Sql.SqlCommands;

public class Coupons {
    public static final String CREATE_TABLE_COUPONS =

            "CREATE TABLE IF NOT EXISTS `couponnnn`.`coupons` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`COMPANY_ID` INT NOT NULL," +
                    "`CATEGORY_ID` INT NOT NULL," +
                    "`TITLE` VARCHAR(45) NOT NULL," +
                    "`DESCRIPTION` VARCHAR(255) NOT NULL," +
                    "`START_DATE` DATE NOT NULL," +
                    "`AMOUNT` INT NOT NULL," +
                    "`PRICE` DOUBLE NOT NULL," +
                    "`IMAGE` VARCHAR(45) NULL," +
                    "`EXPIRATION_DATE` DATE NOT NULL," + // Added expiration_date column
                    "PRIMARY KEY (`ID`)," +
                    "UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE" +
                    ");";
    // Insert a new coupon
    public static final String addCoupon = "INSERT INTO `couponnnn`.`coupons` " +
            "(`COMPANY_ID`, `CATEGORY_ID`, `TITLE`, `DESCRIPTION`, `START_DATE`, `AMOUNT`, `PRICE`, `IMAGE`, `EXPIRATION_DATE`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String deleteExpiredCoupons = "DELETE FROM `couponnnn`.`coupons` "
            + "WHERE ID IN (SELECT ID FROM `couponnnn`.`coupons` WHERE expiration_date <= CURDATE());";
    public static final String getExpiredCouponsQuery = "SELECT * FROM `couponnnn`.`coupons` WHERE expiration_date <= ? ";

    public static final String addCouponPurchase = "INSERT INTO `coupon_purchases` " +
            "(`CUSTOMER_ID`, `COUPON_ID`, `PURCHASE_DATE`, `AMOUNT_PAID`) " +
            "VALUES (?, ?, ?, ?);";
    public static final String getAllCoupons = "SELECT * FROM `couponnnn`.`coupons`";
    public static final String deleteCouponsByCompany = "DELETE FROM `couponnnn`.`coupons` WHERE COMPANY_ID = ?";
            // להוטס
    public static final String getCouponById = "SELECT * FROM `couponnnn`.`coupons` WHERE ID=?";
    public static final String getOneCoupon = "SELECT * FROM `couponnnn`.`coupons` WHERE TITLE=? LIMIT 1";
    public static final String updateCoupon = "UPDATE `couponnnn`.`coupons` SET COLUMN_NAME = ? WHERE ID = ?";
    public static final String deleteCoupon = "DELETE FROM `couponnnn`.`coupons` WHERE ID = ?";
    public static final String deleteCouponPurchase = "DELETE FROM `couponnnn`.`purchases` WHERE COUPON_ID = ?";
    public static final String getAllCouponsByCompany = "SELECT * FROM `couponnnn`.`coupons` WHERE COMPANY_ID = ?";
    public static final String deleteCouponPurchaseHistory = "DELETE FROM `couponnnn`.`purchase_history` WHERE COUPON_ID = ?";
    public static final String getAllCouponsUpToPriceAndCompany = "SELECT * FROM `couponnnn`.`coupons` WHERE COMPANY_ID = ? AND PRICE <= ?;";
    public static final String getAllCouponsByCategoryAndCompany = "SELECT * FROM `couponnnn`.`coupons` WHERE CATEGORY_ID = ? AND COMPANY_ID = ?";
    public static final String getExpiredCoupons = "SELECT * FROM `couponnnn`.`coupons` WHERE expiration_date <= CURDATE()";

}

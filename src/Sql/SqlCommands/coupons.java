package Sql.SqlCommands;

public class coupons {
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
                    "PRIMARY KEY (`ID`)," +
                    "UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE" +
                    ");";

    // Insert a new coupon
    public static final String addCoupon = "INSERT INTO `couponnnn`.`coupons` " +
            "(`COMPANY_ID`, `CATEGORY_ID`, `TITLE`, `DESCRIPTION`, `START_DATE`, `AMOUNT`, `PRICE`, `IMAGE`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    // Get all coupons
    public static final String getAllCoupons = "SELECT * FROM `couponnnn`.`coupons`";

    // Get coupon by ID
    public static final String getCouponById = "SELECT * FROM `couponnnn`.`coupons` WHERE ID=?";

    // Get one coupon by title
    public static final String getOneCoupon = "SELECT * FROM `couponnnn`.`coupons` WHERE TITLE=? LIMIT 1";

    // Delete coupon purchase history by coupon ID
    public static final String DELETECOUPON_PURCHASE_HISTORY_QUERY =
            "DELETE FROM `coupon_purchases` WHERE `coupon_id` = ?";
}

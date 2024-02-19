package Sql.SqlCommands;

public class Customers_VS_Coupons {

    public static final String CREATE_TABLE_CVC = getCreateTableCVC();

    private static String getCreateTableCVC() {
        try {
            return "CREATE TABLE IF NOT EXISTS `couponnnn`.`customers_vs_coupons` (" +
                    "`CUSTOMER_ID` INT NOT NULL," +
                    "`COUPON_ID` INT NOT NULL," +
                    "PRIMARY KEY (`CUSTOMER_ID`, `COUPON_ID`)," +
                    "INDEX `COUPON_ID_IDX` (`COUPON_ID` ASC) VISIBLE," +
                    "CONSTRAINT `CUSTOMER_ID_FK`" +
                    "   FOREIGN KEY (`CUSTOMER_ID`)" +
                    "   REFERENCES `couponnnn`.`customers` (`ID`)" +
                    "   ON DELETE CASCADE" +
                    "   ON UPDATE CASCADE," +
                    "CONSTRAINT `COUPON_ID_FK`" +
                    "   FOREIGN KEY (`COUPON_ID`)" +
                    "   REFERENCES `couponnnn`.`coupons` (`ID`)" +
                    "   ON DELETE CASCADE" +
                    "   ON UPDATE CASCADE" +
                    ");";
        } catch (Exception e) {
            return null;
        }
    }
}

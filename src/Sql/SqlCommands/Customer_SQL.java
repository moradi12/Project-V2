package Sql.SqlCommands;

public class Customer_SQL {

    public static final String CREATE_TABLE_CUSTOMERS =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`customers` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`FIRST_NAME` VARCHAR(45) NOT NULL," +
                    "`LAST_NAME` VARCHAR(45) NOT NULL," +
                    "`EMAIL` VARCHAR(45) NOT NULL," +
                    "`PASSWORD` VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (`ID`)," +
                    "UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE" +
                    ");";

    public static final String addCustomer = "INSERT INTO `couponnnn`.`customers` " +
            "(`FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?, ?);";

    public static final String addCustomerCouponRelation = "INSERT INTO `couponnnn`.`customers_vs_coupons` " +
            "(`CUSTOMER_ID`, `COUPON_ID`) " +
            "VALUES (?, ?);";


    // Update an existing customer
    public static final String updateCustomer = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?";

    // Get all customers
    public static final String getAllCustomers = "SELECT * FROM `couponnnn`.`customers`";

    // Get customer by email
    public static final String getCustomerByEmail = "SELECT * FROM `couponnnn`.`customers` WHERE EMAIL=?";

    public static final String getCustomer = "SELECT * FROM `couponnnn`.`customers` WHERE ID=?";


    public static final String getCouponsOfCustomer = "SELECT * FROM `couponnnn`.`coupons` " +
            "JOIN `couponnnn`.`customers_vs_coupons` ON `coupons`.`ID` = `customers_vs_coupons`.`COUPON_ID` " +
            "WHERE `customers_vs_coupons`.`CUSTOMER_ID` = ?";


    public static final String getLastInsertId = "SELECT LAST_INSERT_ID(); "  ;

    public static final String isCustomerExistBot = "SELECT EXISTS(" +
            "SELECT 1 FROM `couponnnn`.`customers` " +
            "WHERE ID=? OR EMAIL=?)";

    public static final String deleteCustomer = "DELETE FROM `couponnnn`.`customers` WHERE ID = ?";
    public static final String buyCupon = "INSERT INTO `couponnnn`.`customer_coupon` (`CUSTOMER_ID`, `COUPON_ID`) VALUES (?, ?)";

    public static final String IsCustomerExist = "SELECT COUNT(*) FROM `couponnnn`.`customers` WHERE ID = ?";
}


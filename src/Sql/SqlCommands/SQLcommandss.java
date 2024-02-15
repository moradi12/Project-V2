package Sql.SqlCommands;

public final class SQLcommandss {

    // Insert a new company
    public static final String addCompany = "INSERT INTO `couponnnn`.`companies` " +
            "(`NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?);";

    // Get all companies
    public static final String getAllCompanies = "SELECT * FROM `couponnnn`.`companies`";

    // Get company by email and password
    public static final String getCompanyByEmailAndPassword = "SELECT * FROM `couponnnn`.`companies` " +
            "WHERE EMAIL=? AND PASSWORD=?";

    // Get company by ID
    public static final String getCompanyById = "SELECT * FROM `couponnnn`.`companies` WHERE idCOMPANIES=?";

    // Insert a new coupon
    public static final String addCoupon = "INSERT INTO `couponnnn`.`coupons` " +
            "(`COMPANY_ID`, `CATEGORY_ID`, `TITLE`, `DESCRIPTION`, `START_DATE`, `AMOUNT`, `PRICE`, `IMAGE`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    // Get all coupons
    public static final String getAllCoupons = "SELECT * FROM `couponnnn`.`coupons`";

    // Get coupon by ID
    public static final String getCouponById = "SELECT * FROM `couponnnn`.`coupons` WHERE ID=?";

    // Insert a new customer
    public static final String addCustomer = "INSERT INTO `couponnnn`.`customers` " +
            "(`FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?, ?);";

    // Get all customers
    public static final String getAllCustomers = "SELECT * FROM `couponnnn`.`customers`";

    // Get customer by email
    public static final String getCustomerByEmail = "SELECT * FROM `couponnnn`.`customers` WHERE EMAIL=?";

    // Insert a customer-coupon relationship
    public static final String addCustomerCouponRelation = "INSERT INTO `couponnnn`.`customers_vs_coupons` " +
            "(`CUSTOMER_ID`, `COUPON_ID`) " +
            "VALUES (?, ?);";

    // Get coupons of a customer
    public static final String getCouponsOfCustomer = "SELECT * FROM `couponnnn`.`coupons` " +
            "JOIN `couponnnn`.`customers_vs_coupons` ON `coupons`.`ID` = `customers_vs_coupons`.`COUPON_ID` " +
            "WHERE `customers_vs_coupons`.`CUSTOMER_ID` = ?";
}

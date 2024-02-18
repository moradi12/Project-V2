package Sql.SqlCommands;

public class Categories {
    public static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`categories` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT," +
                    "`NAME` VARCHAR(45) NOT NULL," +
                    "`DESCRIPTION` VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (`ID`)" +
                    ");";

    public static final String ADD_CATEGORY = "INSERT INTO `couponnnn`.`categories` " +
            "(`NAME`, `DESCRIPTION`) " +
            "VALUES (?, ?)";

    public static final String GET_ALL_CATEGORIES = "SELECT * FROM `couponnnn`.`categories`";
    public static final String GET_ALL_COUPONS = "SELECT * FROM `couponnnn`.`coupons` WHERE `CATEGORY_ID`=?";

    public static final String GET_CATEGORY_BY_ID = "SELECT * FROM `couponnnn`.`categories` WHERE ID=?";
}

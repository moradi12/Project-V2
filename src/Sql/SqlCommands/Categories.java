// categories class
package Sql.SqlCommands;

public class Categories {
    public static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`categories` (" +
                    "`ID` INT NOT NULL AUTO_INCREMENT, " +
                    "`NAME` VARCHAR(45) NULL, " +
                    "`DESCRIPTION` TEXT NULL, " +
                    "`CREATED_AT` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "PRIMARY KEY (`ID`));";

    // Insert a new category query
    public static final String ADD_CATEGORY = "INSERT INTO `couponnnn`.`categories` " +
            "(`NAME`, `DESCRIPTION`) " +
            "VALUES (?, ?)";

    // Get all categories query
    public static final String GET_ALL_CATEGORIES = "SELECT * FROM `couponnnn`.`categories`";

    // Get category by ID query
    public static final String GET_CATEGORY_BY_ID = "SELECT * FROM `couponnnn`.`categories` WHERE ID=?";

}

package Sql.SqlCommands;

public class companies {
    public static final String CREATE_TABLE_COMPANIES =
            "CREATE TABLE IF NOT EXISTS `couponnnn`.`companies` (" +
                    "`idCOMPANIES` INT NOT NULL AUTO_INCREMENT," + // Add AUTO_INCREMENT here
                    "`NAME` VARCHAR(45) NULL," +
                    "`EMAIL` VARCHAR(45) NULL," +
                    "`PASSWORD` VARCHAR(45) NULL," +
                    "PRIMARY KEY (`idCOMPANIES`)," +
                    "UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE" +
                    ");";


    public static final String addCompany = "INSERT INTO `couponnnn`.`companies` " +
            "(`NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?);";
    public static final String GET_ALL_COMPANIES = "SELECT * FROM `couponnnn`.`companies`";

    public static final String getCompanyByEmailAndPassword = "SELECT * FROM `couponnnn`.`companies` " +
            "WHERE EMAIL=? AND PASSWORD=?";

    public static final String getCompanyByEmail = "SELECT * FROM `couponnnn`.`companies` WHERE `EMAIL`=?";

    public static final String IsCompanyExist = "SELECT COUNT(*) FROM `couponnnn`.`companies` WHERE EMAIL=? AND PASSWORD=?";

    public static final String getCompanyById = "SELECT * FROM `couponnnn`.`companies` WHERE idCOMPANIES=?";

    public static final String updateCompany = "UPDATE `couponnnn`.`companies` " +
            "SET `NAME`=?, `EMAIL`=?, `PASSWORD`=? " +
            "WHERE `ID`=?";

    public static final String deleteCompany = "DELETE FROM `couponnnn`.`companies` WHERE `idCOMPANIES`=?";

    public static final String getCompanyDetails = "SELECT * FROM `couponnnn`.`companies` WHERE `ID`=?";
}

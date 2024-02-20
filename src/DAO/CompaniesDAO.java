package DAO;


import beans.Company;
import exception.DatabaseQueryException;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {

    boolean isCompanyExists(String email, String password);
    void addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(int companyID);
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID) throws DatabaseQueryException;



}

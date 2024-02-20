package DBDAO;

import DAO.CompaniesDAO;
import Sql.DButils;
import Sql.SqlCommands.Companies;
import beans.Company;
import exception.CompanyQueryException;
import exception.DatabaseQueryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {

    /**
     * Implementation of the methods from dao
     */


    /**
     * Checks if a company exists in the database based on the provided email
     * and password.
     *
     * @param email    The email of the company to check
     * @param password The password of the company to check
     * @return true if the company exists, false otherwise
     * @throws CompanyQueryException If an error occurs during the query execution
     */
    @Override
    public boolean isCompanyExists(String email, String password) {
        Map<Integer, Object> params = Map.of(1, email, 2, password);
        try (ResultSet resultSet = DButils.runQueryForResult(Companies.IsCompanyExist, params)) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            } else {
                // Throw an exception with a clearer message when no result is returned
                throw new CompanyQueryException("Failed to determine if the company exists in the database: No result returned from the query.");
            }
        } catch (SQLException e) {
            // Provide a more specific message for SQLException
            throw new CompanyQueryException("Failed to check if company exists in the database due to a SQL error", e);
        }
    }

    /**
     * Adds a new company to the database
     */

    @Override
    public void addCompany(Company company) {
        if (isCompanyExists(company.getEmail(), company.getPassword())) {
            throw new RuntimeException("Failed to add company to the database: Company already exists.");
        }
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());

        // Check if the query execution was successful
        if (DButils.runQuery(Companies.addCompany, params)) {
            System.out.println("Company added successfully.");
        } else {
            throw new RuntimeException("Failed to add company to the database");
        }
    }    /**
     * Updates an existing company in the database
     */
    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, company.getId());
        if (DButils.runQuery(Companies.updateCompany, params)) {
            System.out.println("Company updated");
        } else {
            throw new RuntimeException("Failed to update company in the database");
        }

    }


    /**
     * Deletes a company from the database based on its ID
     */
    @Override
    public void deleteCompany(int companyID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        if (DButils.runQuery(Companies.deleteCompany, params)) {
            System.out.println("Company deleted");
        } else {
            throw new RuntimeException("Failed to delete company from the database");
        }
    }

    /**
     * Retrieves a list of all companies from the database
     * @return A list containing all companies
     * */
    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            Map<Integer, Object> params = new HashMap<>();
            ResultSet resultSet = DButils.runQueryForResult(Companies.GET_ALL_COMPANIES, params);
            while (resultSet.next()) {
                Company company = ResultSetUtils.mapResultSetToCompany(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to retrieve all companies from the database", e);
        }
        return companies;
    }


    /**
     * Retrieves details of a specific company from the database based on its ID
     */
    @Override
    public Company getOneCompany(int companyID) throws DatabaseQueryException {
        try {
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, companyID);
            ResultSet resultSet = DButils.runQueryForResult(Companies.getCompanyById, params);
            if (resultSet.next()) {
                return ResultSetUtils.mapResultSetToCompany(resultSet);
            } else {
                System.out.println("Company with ID " + companyID + " does not exist.");
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to retrieve company details from the database", e);
        }
    }

}

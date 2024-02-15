package DBDAO;

import DAO.CompaniesDAO;
import Sql.DButils;
import Sql.SqlCommands.companies;
import beans.Company;
import beans.Coupon;
import exception.DatabaseQueryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {
    @Override
    public boolean isCompanyExists(String email, String password) {
        Map<Integer, Object> params = Map.of(1, email, 2, password);
        try (ResultSet resultSet = DButils.runQueryForResult(companies.IsCompanyExist, params)) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to check if company exists in the database", e);
        }
    }

    @Override
    public void addCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        if (DButils.runQuery(companies.addCompany, params)) {
            System.out.println("Company added ");
        }
        throw new RuntimeException("Failed to add company to the database");
    }
    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, company.getId());
        if (DButils.runQuery(companies.updateCompany, params)) {
            System.out.println("Company updated");
        } else {
            throw new RuntimeException("Failed to update company in the database");
        }

    }
    @Override
    public void deleteCompany(int companyID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        if (DButils.runQuery(companies.deleteCompany, params)) {
            System.out.println("Company deleted");
        } else {
            throw new RuntimeException("Failed to delete company from the database");
        }
    }
    //// is it null ?!
//////////////////////////////////TODO LIST IS NOT WORKING
    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            Map<Integer, Object> params = new HashMap<>();
            ResultSet resultSet = DButils.runQueryForResult(Sql.SqlCommands.companies.getAllCompanies, params);
            while (resultSet.next()) {
                Company company = ResultSetUtils.mapResultSetToCompany(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to retrieve all companies from the database", e);
        }
        return companies;
    }
    @Override
    public Company getOneCompany(int companyID) {
        try {
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, companyID);
            ResultSet resultSet = DButils.runQueryForResult(Sql.SqlCommands.companies.getCompanyById, params);
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
    @Override
    public Company getCompanyDetails(String email) {
        Map<Integer, Object> params = Map.of(1, email);
        try (ResultSet resultSet = DButils.runQueryForResult(companies.getCompanyByEmail, params)) {
            if (resultSet.next()) {
                int id = resultSet.getInt("idCOMPANIES");
                String name = resultSet.getString("NAME");
                String password = resultSet.getString("PASSWORD");
                CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
                List<Coupon> list = couponsDBDAO.getAllCouponsByCompany(id);
                return new Company(id, name, email, password, list);
            } else {
                System.out.println("Company with email " + email + " does not exist.");
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Failed to retrieve company details from the database", e);
        }
    }
}

// DButils class
package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class DButils {
    public static boolean runQuery(String sql) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (sql.toLowerCase().contains("delete")) {
                return false;
            }

            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean runQuery(String sql, Map<Integer, Object> params) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            params.forEach((key, value) -> {
                try {
                    setParameter(preparedStatement, key, value);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static ResultSet runQueryForResult(String sql, Map<Integer, Object> params) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            params.forEach((key, value) -> {
                try {
                    setParameter(preparedStatement, key, value);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println(preparedStatement);
            return preparedStatement.executeQuery();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setParameter(PreparedStatement preparedStatement, int key, Object value) throws SQLException {
        if (value instanceof Integer) {
            preparedStatement.setInt(key, (Integer) value);
        } else if (value instanceof String) {
            preparedStatement.setString(key, String.valueOf(value));
        } else if (value instanceof Date) {
            preparedStatement.setDate(key, (java.sql.Date) value);
        } else if (value instanceof Double) {
            preparedStatement.setDouble(key, (Double) value);
        } else if (value instanceof Boolean) {
            preparedStatement.setBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            preparedStatement.setFloat(key, (Float) value);
        }
    }

    }


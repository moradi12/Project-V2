// ConnectionPool class
package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    private static final int NUMBER_OF_CONNECTION = 10;
    public static ConnectionPool instance = null;
    private final Stack<Connection> connections = new Stack<>();

    //ConnectionPool//
    private ConnectionPool() {
        System.out.println("We created a new connection pool instance");
        try {
            openAllConnections();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void openAllConnections() throws SQLException {
        for (int counter = 0; counter < NUMBER_OF_CONNECTION; counter++) {
            Connection connection = DriverManager.getConnection(DBmanager.URL, DBmanager.SQL_USER, DBmanager.SQL_PASSWORD);
            connections.push(connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public void closeAllConnections() throws InterruptedException {
        synchronized (connections) {
            while (connections.size() < NUMBER_OF_CONNECTION) {
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                connections.wait();
            }
            return connections.pop();
        }
    }

    public void restoreConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            connections.notify();
        }
    }

    public void returnConnection(Connection connection) {
    }
}

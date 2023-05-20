package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static String user = "postgres";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String password = "dasham771";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(url, user, password);

        }
        return connection;
    }
}

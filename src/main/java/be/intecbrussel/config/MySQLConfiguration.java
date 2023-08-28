package be.intecbrussel.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConfiguration {
    public static Connection getConnection(){
        String user = "Jan";
        String password = "password";
        String url = "localhost";
        String port = "3306";
        String database = "userdb";
        String connectionString = String.format("jdbc:mysql://%s:%s/%s",url,port,database);
        Properties connectionProperties = new Properties();
        connectionProperties.put("User",user);
        connectionProperties.put("Password",password);
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString,connectionProperties);
        } catch (SQLException e) {
            System.out.println("ERROR: Sorry no connection");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
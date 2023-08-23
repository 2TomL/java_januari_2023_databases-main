package be.intecbrussel.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConfiguration {
    public static Connection getConnection(){
        String user = "root";
        String passw = "toor";
        String url = "localhost";
        String port = "3306";
        String database = "accountapp";

        String connectionString = String.format("jdbc:mysql://%s:%s/%s", url,port,database);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user",user);
        connectionProperties.put("passw", passw);

        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString,connectionProperties);
        } catch (SQLException e){
            System.err.println("ERROR: Could not connect to DB");
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void main(String[]args){
        getConnection();
    }
}

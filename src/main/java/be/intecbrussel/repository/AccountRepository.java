package be.intecbrussel.repository;

import be.intecbrussel.config.MySQLConfiguration;
import be.intecbrussel.model.Account;
import com.mysql.cj.exceptions.ConnectionIsClosedException;

import java.sql.*;
import java.util.Optional;

public class AccountRepository {
    public boolean createAccount(Account account){
        // send account to database
        String query = String.format("INSERT INTO Account VALUES (?,?)");
        try ( Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,account.getEmail());
            statement.setString(2,account.getPassword());
            statement.executeUpdate();
            return true;
        }catch (Exception  e){
            System.out.println(e);
            return false;
        }

    }
    public Optional<Account> accountAuthentication(String email) {
        String query = String.format(" select * from Account where email like ?");

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Account account = new Account(resultSet.getString("email"),resultSet.getString("password"));
                return Optional.of(account);
            }
        }catch (Exception  e){
            System.out.println(e + " (Account Repository)");
        }
        return Optional.empty();
    }

    public boolean deleteAccount(String email) {
        String query = String.format("Delete from Account where email like ?");

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.executeUpdate();
            return true;
        }catch (Exception  e){
            System.out.println(e + "User Repository");
            return false;
        }
    }

    public boolean changePassword(String email, String newPassword) {
        String query = String.format(" UPDATE Account SET password =? WHERE email LIKE ?");

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,newPassword);
            statement.setString(2,email);
            statement.executeUpdate();
            return true;
        }catch (Exception  e){
            System.out.println(e + "User Repository");
            return false;
        }
    }
}
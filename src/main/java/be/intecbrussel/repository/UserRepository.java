package be.intecbrussel.repository;

import be.intecbrussel.config.MySQLConfiguration;
import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class UserRepository {
    public boolean createUser(User user) {
        //send user to database
        String query = String.format("INSERT INTO user VALUES (?,?,?,?);");

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, IdGenerator.generateID());
            statement.setString(2,user.getFname());
            statement.setString(3,user.getLname());
            statement.setString(4,user.getAccount().getEmail());
            statement.executeUpdate();
            connection.close();
            return true;
        }catch (Exception  e){
            System.err.println("USER COULD NOT BE CREATED");
            e.printStackTrace();
            return false;
        }
    }

    public Optional<User> getUserInfo(Account account) {
        String preparedQuery = String.format(" select * from user where accEmail like ?");

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setString(1,account.getEmail());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getString("fName"),resultSet.getString("lName"),account);
                return Optional.of(user);
            }
        }catch (Exception  e){
            System.out.println(e + " (User Repo)");
        }
        return Optional.empty();
    }

    public int getId(String email) {
        String query = String.format(" delete  from user where accEmail like \"%s\";",email);

        try (Connection connection = MySQLConfiguration.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return  resultSet.getInt(1);
            }
        }catch (Exception  e){
            System.out.println(e + " (id Repo)");
        }
        return  0;
    }

    public boolean deleteUser(String email) {
        String query = String.format("delete from user where accEmail like ?");

        try (Connection connection = MySQLConfiguration.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.executeUpdate();
            return true;
        }catch (Exception  e){
            System.out.println(e + "User Repo");
            return false;
        }

    }
}
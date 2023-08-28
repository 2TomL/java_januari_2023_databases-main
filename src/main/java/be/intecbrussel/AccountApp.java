package be.intecbrussel;

import be.intecbrussel.config.MySQLConfiguration;
import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.service.LoginService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountApp {
    public static void main(String[] args) {

        try (Connection connection = MySQLConfiguration.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("fName")+" "+
                        resultSet.getString(3)+" "+ resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello User Abuser");
        System.out.println("1.Register - 2. Login - 3. Add multiple users");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> batchInsert();
        }
    }
    private static void batchInsert(){
        List<User> userList = new ArrayList<>();
        for (int i = 5; i > 0; i-- ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter first name");
            String fname = scanner.nextLine();

            System.out.println("Enter last name");
            String lname = scanner.nextLine();

            System.out.println("Enter email");
            String email = scanner.nextLine();

            System.out.println("Enter password");
            String passw = scanner.nextLine();

            User user = new User(fname,lname,new Account(email, passw));
            userList.add(user);
        }
        LoginService loginService = new LoginService();
        loginService.registerManyUsers(userList);
    }
    private static void register(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your first name");
        String fname = scanner.nextLine();

        System.out.println("What is your last name");
        String lname = scanner.nextLine();

        System.out.println("What is your email");
        String email = scanner.nextLine();

        System.out.println("What is your password");
        String password = scanner.nextLine();

        LoginService loginService = new LoginService();
        boolean success = loginService.register(fname,lname,email,password);
        if (success){
            System.out.println("register success");
        }else System.out.println("INVALID email");
    }
    private static void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your email");
        String email = scanner.nextLine();

        System.out.println("What is your password");
        String password = scanner.nextLine();
        LoginService loginService = new LoginService();

        Optional<User> user = loginService.login(email,password);
        if (user.isPresent()){
            User user1 = user.get();
            System.out.println("Hello "+user1.getFname()+" "+user1.getLname());
            System.out.println("0-settings");
            int input = scanner.nextInt();
            switch (input){
                case 0: settings(user1);
                    break;
            }
        }
        else System.out.println("INVALID INPUT");
    }
    private static void settings(User user){
        LoginService loginService = new LoginService();
        System.out.println("settings");
        System.out.println("1-change password 2-delete account");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input){
            case 1:
                break;
            case 2:
                break;
        }
    }
}
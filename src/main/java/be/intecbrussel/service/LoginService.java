package be.intecbrussel.service;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.repository.AccountRepository;

import java.util.Optional;

public class LoginService {
    private UserService userService = new UserService();

    public boolean register(String fname, String lname, String email, String password) {
        Account account = new Account(email,password);
        User user = new User(fname,lname,account);
        return userService.createUser(user);
    }

    public Optional<User> login(String email, String password) {
        Optional<Account> account = userService.loginToRepository(email);
        if (account.isPresent()){
            if (account.get().getPassword().equals(password)){
                Optional<User> user = userService.getUserInfo(account.get());
                return user;
            }
        }
        return Optional.empty();
    }
}

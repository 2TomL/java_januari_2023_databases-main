package be.intecbrussel.service;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.repository.AccountRepository;

public class LoginService {
    private UserService userService = new UserService();
    private AccountRepository accountRepository = new AccountRepository();

    public boolean register(String fname, String lname, String email, String passw) {
        Account account = new Account(email, passw);
        User user = new User(fname, lname, account);

        return userService.createUser(user);
    }
    public boolean login(String email, String password){
    return accountRepository.LoginToRepository(email, password);
    }
}

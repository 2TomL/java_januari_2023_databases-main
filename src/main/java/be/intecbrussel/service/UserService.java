package be.intecbrussel.service;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private AccountService accountService = new AccountService();
    private UserRepository userRepository = new UserRepository();
    public boolean createUser(User user) {

        return accountService.createAccount(user.getAccount())&userRepository.createUser(user);
    }

    public Optional<Account> loginToRepository(String email) {
        Optional<Account> account = accountService.accountAuthentication(email);
        return account;
    }

    public Optional<User> getUserInfo(Account account) {
        Optional<User> user = userRepository.getUserInfo(account);
        return user;
    }
    public void createManyUsers(List<User> userList){
        List<Account> accountList = new ArrayList<>();
        for (User user : userList){
            accountList.add(user.getAccount());
        }
        accountService.createManyAccounts(accountList);
        userRepository.createManyUsers(userList);
    }
    public boolean deleteAccount(String email){
        return userRepository.deleteUser(email) & accountService.deleteAccount(email);
    }
    public boolean changePassword(String email,String newPassword){
        return accountService.changePassword(email,newPassword);
    }
}
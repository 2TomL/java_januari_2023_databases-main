package be.intecbrussel.service;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    boolean createUser(User user);
    Optional<Account> loginToRepository(String email);
    Optional<User> getUserInfo(Account account);
    void createManyUsers(List<User> userList);
    boolean deleteAccount(String email);
}

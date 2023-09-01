package be.intecbrussel.repository;

import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    boolean createUser(User user);
    Optional<User> getUserInfo(Account account);
    int getId(String email);
    boolean deleteUser(String email);
    void createManyUsers(List<User> users);
}

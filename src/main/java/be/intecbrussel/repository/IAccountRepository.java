package be.intecbrussel.repository;

import be.intecbrussel.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    boolean createAccount(Account account);
    Optional<Account> accountAuthentication(String email);
    boolean deleteAccount(String email);
    boolean changePassword(String email, String newPassword);
    void createManyAccounts (List<Account> accountList);

}

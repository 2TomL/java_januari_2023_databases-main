package be.intecbrussel.service;

import be.intecbrussel.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    boolean createAccount(Account account);
    Optional<Account> accountAuthentication(String email);
    void createManyAccounts(List<Account> accountList);
    boolean deleteAccount(String email);
    boolean changePassword(String email, String newPassword);
}

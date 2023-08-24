package be.intecbrussel.service;

import be.intecbrussel.model.Account;
import be.intecbrussel.repository.AccountRepository;

import java.util.Optional;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    public boolean createAccount(Account account) {
        return accountRepository.createAccount(account);
    }

    public Optional<Account> accountAuthentication(String email) {
        Optional<Account> account = accountRepository.accountAuthentication(email);
        return account;
    }

    public boolean deleteAccount(String email) {
        return accountRepository.deleteAccount(email);
    }

    public boolean changePassword(String email, String newPassword) {
        return accountRepository.changePassword(email,newPassword);
    }
}
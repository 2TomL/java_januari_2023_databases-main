package be.intecbrussel.repository;

import be.intecbrussel.model.Account;

public class AccountRepository {

    public boolean createAccount(Account account) {
        String query = String.format("INSERT INTO Account VALUES ( '%s' , '%s' );", account.getEmail(), account.getPassw());

        System.out.println(query);
        return true;
    }

    public boolean LoginToRepository (String email, String password) {
    String query = String.format("select * from Account " + "where email like \"%s\" and password like \"%s\" ;", email,password);

    System.out.println(query);
    return true;

    }
}

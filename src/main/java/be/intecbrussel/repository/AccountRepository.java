package be.intecbrussel.repository;

import be.intecbrussel.config.EMFConfiguration;
import be.intecbrussel.model.Account;
import jakarta.persistence.EntityManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements IAccountRepository{
    private EntityManager em;
    public boolean createAccount(Account account){
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        return  true;
    }

    public Optional<Account> accountAuthentication(String email) {
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        Account account = em.find(Account.class,email);
        em.getTransaction().commit();
        em.close();
        return Optional.of(account);
    }

    public boolean deleteAccount(String email) {
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        Account account = em.find(Account.class,email);
        if (account != null){
            em.remove(account);
            em.getTransaction().commit();
            em.close();
            return true;
        }else {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
    }

    public boolean changePassword(String email, String newPassword) {
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        Account account = em.find(Account.class, email);
        if (account != null) {
            account.setPassword(newPassword);
            em.getTransaction().commit();
            em.close();
            return true; // Password changed ok
        } else {
            em.getTransaction().rollback();
            em.close();
            return false; // Account not ok
        }
    }
    public void createManyAccounts (List<Account> accountList){
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        for (Account account : accountList) {
            em.persist(account);
        }
        em.getTransaction().commit();
        em.close();
    }
}

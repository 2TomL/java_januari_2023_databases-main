package be.intecbrussel.repository;

import be.intecbrussel.config.EMFConfiguration;
import be.intecbrussel.model.Account;
import be.intecbrussel.model.User;
import be.intecbrussel.util.IdGenerator;
import com.sun.source.tree.TryTree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository{
    private EntityManager em;
    public boolean createUser(User user) {
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public Optional<User> getUserInfo(Account account) {
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, account.getEmail());
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(user);
    }

    public int getId(String email) {
        em = EMFConfiguration.getConnection().createEntityManager();
        Query query = em.createQuery("SELECT u.id FROM User u WHERE u.account.email = :email");
        query.setParameter("email", email);
        Integer id = (Integer) query.getSingleResult();
        em.close();
        return id != null ? id : -1; // Return -1 if not found, handle exceptions accordingly
    }

    public boolean deleteUser(String email) {
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, email);
        if (user != null) {
            em.remove(user);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.close();
        return false; // Not found
    }

    public void createManyUsers(List<User>users){
        em = EMFConfiguration.getConnection().createEntityManager();
        em.getTransaction().begin();
        for (User user : users) {
            em.persist(user);
        }
        em.getTransaction().commit();
        em.close();
    }
}
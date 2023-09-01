package be.intecbrussel.model;

import be.intecbrussel.repository.UserRepository;
import be.intecbrussel.util.IdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class User {
    @Id
    // nu via Id generator class ... maar ook mogelijk via
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fName;
    private String lName;
    @OneToOne
    private Account account;

    private User(long id, String fName, String lName, Account account) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.account = account;
    }

    public User(String fName,String lName,Account account){

        this(IdGenerator.generateID(),fName,lName,account);
    }

    public long getId() {
        UserRepository userRepository = new UserRepository();
        this.id = userRepository.getId(account.getEmail());
        return id;
    }

    public String getFname() {
        return fName;
    }
    public String getLname() {
        return lName;
    }
    public void setFname(String fName) {
        this.fName = fName;
    }
    public void setLname(String lName) {
        this.lName = lName;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fName + '\'' +
                ", lname='" + lName + '\'' +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && Objects.equals(fName, user.fName) && Objects.equals(lName, user.lName) && Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, account);
    }
}

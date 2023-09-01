package be.intecbrussel.service;

import be.intecbrussel.model.User;

import java.util.List;
import java.util.Optional;

public interface ILoginService {
    boolean register(String fname, String lname, String email, String password);
    Optional<User> login(String email, String password);
    void registerManyUsers (List<User> userList);
}

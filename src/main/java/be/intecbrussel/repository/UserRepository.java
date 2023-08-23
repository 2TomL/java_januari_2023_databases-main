package be.intecbrussel.repository;

import be.intecbrussel.model.User;

public class UserRepository {

    public boolean createUser(User user) {
        String query = String.format("INSERT INTO User VALUES ('%d', '%s', '%s', '%s');",
                user.getId(), user.getFname(), user.getLname(), user.getAccount().getEmail());

        System.out.println(query);
        return true;
    }
    public void getUserInfo(String email) {
        String query = String.format(" select * from user where email like \"%s\";",email);
    }
}

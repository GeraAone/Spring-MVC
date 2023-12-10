package DAO;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User addUser(String name, String email, int age);
    void deleteUser(Long id);
    User getUserById(Long id);
    void updateUser(User newUser);

}

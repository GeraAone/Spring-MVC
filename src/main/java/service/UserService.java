package service;

import model.User;

import java.util.List;

public interface UserService
{
    List<User> getAllUsers();
    User addUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    void updateUser(User newUser);
}

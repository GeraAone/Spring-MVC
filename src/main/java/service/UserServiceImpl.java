package service;

import DAO.UserDaoImpl;
import lombok.NoArgsConstructor;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService
{
    private UserDaoImpl userDao;

    @Override
    public List<User> getAllUsers() {
       return userDao.getAllUsers();
    }

    @Override
    public User addUser(User user) {
        String name = user.getName();
        String email = user.getEmail();
        int age = user.getAge();
        return userDao.addUser(name,email, age);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(User newUser) {
        userDao.updateUser(newUser);
    }
}

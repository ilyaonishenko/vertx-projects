package DAO;

import models.User;

import java.util.List;

/**
 * Created by woqpw on 27.09.15.
 */
public interface UserDAO {
    public List<User> getAllUsers();
    public User getUser(int id);
    public void updateUser(User user);
    public void deleteUser(User user);
    public void addUser(User user);
}

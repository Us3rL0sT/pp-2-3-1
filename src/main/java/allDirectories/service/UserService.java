package allDirectories.service;

import allDirectories.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void delete(int id);

    void add(User user);

    void update(int id, User user);

    User get(int id);
}

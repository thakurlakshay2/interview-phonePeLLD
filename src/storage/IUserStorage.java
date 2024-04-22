package storage;

import models.User;

import java.util.Map;

public interface IUserStorage {
    public User getUser(String userName);
    public void addUser(User user);

    public Map<String, User> getUserMap();
}

package com.luckypets.dao;

import com.luckypets.entity.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User getUser(int id);
    User getUser(String login);

    List<User> getAllUsers();

    User getUserWithAllInfo(String login);
}

package com.luckypets.dao;

import com.luckypets.entity.User;

public interface UserDao {
    void saveUser(User user);
    User getUser(String name);
}

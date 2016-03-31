package com.luckypets.service;


import com.luckypets.entity.User;

public interface UserService {

    User getUser(String login);

    void saveUser(User user);

}

package com.luckypets.service;

import com.luckypets.dao.UserDao;
import com.luckypets.entity.User;
import com.luckypets.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String login) {
        //User user = new User(login, "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        //user.setRole(UserRole.ROLE_USER);
        User user = userDao.getUser(login);
        if (user == null){
            throw new UsernameNotFoundException("login for \"" + login + "\" failed");
        }

        return user;
    }

   /* @Autowired
    ShaPasswordEncoder passwordEncoder;*/

    @Override
    public void saveUser(User user) {

        user.setRole(UserRole.ROLE_USER);
        userDao.saveUser(user);
        System.out.println("saving user: " + user.getLogin());

    }



}

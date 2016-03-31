package com.luckypets.service;

import com.luckypets.dao.UserDao;
import com.luckypets.entity.User;
import com.luckypets.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String login) {
        //User user = new User(login, "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        //user.setRole(UserRole.ROLE_USER);
        User user = userDao.getUser(login);
        System.out.println("searching for user " + login + "found: " + user.getLogin());
        if (user == null){
            throw new UsernameNotFoundException("login for \"" + login + "\" failed");
        }

        return user;
    }

    @Autowired
    ShaPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(
                codePassword(user.getPassword())
        );
        user.setRole(UserRole.ROLE_USER);
        userDao.saveUser(user);
        System.out.println("saving user: " + user.getLogin());

    }

    private String codePassword(String realPassword) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Cant initialize password coder");
        }
        md.update(realPassword.getBytes());

        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}

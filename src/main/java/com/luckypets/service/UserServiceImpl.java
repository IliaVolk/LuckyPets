package com.luckypets.service;

import com.luckypets.dao.AdvertCommentDao;
import com.luckypets.dao.AdvertDao;
import com.luckypets.dao.UserDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;
import com.luckypets.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private UserAuthenticationSupport authenticationSupport;

    @Autowired
    private AdvertCommentDao advertCommentDao;
    @Override
    public User getUser(String login) {

        User user = userDao.getUser(login);
        if (user == null){
            throw new UsernameNotFoundException("login for \"" + login + "\" failed");
        }

        return user;
    }

    @Override
    public void saveUser(User user) {

        user.setRole(UserRole.ROLE_USER);
        userDao.saveUser(user);
        System.out.println("saving user: " + user.getLogin());

    }

    @Override
    public List<Advert> getAdverts(int beginIndex, int count) {
        User user = userDao.getUser(authenticationSupport.getCurrentUserName());
        return advertDao.getAdverts(user, beginIndex, count);
    }

    @Override
    public List<AdvertComment> getComments(int beginIndex, int count) {
        User user = userDao.getUser(authenticationSupport.getCurrentUserName());
        return advertCommentDao.getComments(user, beginIndex, count);
    }


}

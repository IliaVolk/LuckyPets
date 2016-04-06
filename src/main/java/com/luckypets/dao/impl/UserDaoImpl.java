package com.luckypets.dao.impl;

import com.luckypets.dao.UserDao;
import com.luckypets.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
/**
 * should not be used manually for saving users
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        getSession().saveOrUpdate(user);
    }

    @Override
    @SuppressWarnings("unckecked")
    /*request to data base includes criteria "User.class"
    and from list got one element*/
    @Transactional
    public User getUser(String login) {
        return (User) getSession().createCriteria(User.class).
                add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> getAllUsers() {
        return getSession().createCriteria(User.class).list();
    }

    @Override
    public User getUserWithAllInfo(String login) {
        User user = (User) getSession().createCriteria(User.class).
                add(Restrictions.eq("login", login)).uniqueResult();
        //crutch for unproxy lazy collections
        //TODO: do something
        user.getClinicComments().get(0);
        user.getAdvertComments().get(0);
        user.getAdverts().get(0);
        return user;
    }
}

package com.luckypets.dao;

import com.luckypets.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
    @Override
    public void saveUser(User user) {
        getHibernateTemplate().persist(user);
    }

    @Override
    @SuppressWarnings("unckecked")
    /*request to data base includes criteria "User.class"
    and from list got one element*/
    public User getUser(String login) {
        return (User) getSession().createCriteria(User.class).
                add(Restrictions.eq("login", login)).list().get(0);
    }
}

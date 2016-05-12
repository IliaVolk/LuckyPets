package com.springapp.mvc;

import com.luckypets.config.DataSourceConfig;
import com.luckypets.config.HibernateConfig;
import com.luckypets.config.Initializer;
import com.luckypets.config.WebAppConfig;
import com.luckypets.dao.AdvertCommentDao;
import com.luckypets.dao.AdvertDao;
import com.luckypets.dao.UserDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Initializer.class,
        WebAppConfig.class, HibernateConfig.class, DataSourceConfig.class})
public class TestAdvertMapping {
    @Autowired
    AdvertDao advertDao;
    @Autowired
    UserDao userDao;
    @Autowired
    AdvertCommentDao advertCommentDao;

    @Test
    public void getAdvertWithId3() {
        Advert advert = advertDao.getAdvertWithComments(3);
        System.out.println(advert);
        System.out.println(advert.getAdvertComments());
        User user = userDao.getUserWithAllInfo(
                advert.getUser().getLogin()
        );
        System.out.println(user.getAdvertComments());
        System.out.println(user.getClinicComments());
        System.out.println(user.getAdverts());

    }


    //@Test
    @Transactional
    public void testSaving() {
        Advert advert = new Advert();
        User user = userDao.getAllUsers().get(0);
        advert.setUser(user);
        advert.setText("advert1 text");
        advert.setTitle("advert1");
        advert.setAdvertType(AdvertType.GIVE);
        advert.setAnimalTypes(EnumSet.of(AnimalType.CAT, AnimalType.DOG));
        advertDao.saveAdvert(advert);

        AdvertComment advertComment1 = new AdvertComment();
        advertComment1.setAdvert(advert);
        advertComment1.setText("advert comment text1");
        advertComment1.setUser(user);
        AdvertComment advertComment2 = new AdvertComment();
        advertComment2.setAdvert(advert);
        advertComment2.setUser(user);
        advertComment2.setText("advert comment text2");
        advertCommentDao.saveComment(advertComment1);
        advertCommentDao.saveComment(advertComment2);
    }
}

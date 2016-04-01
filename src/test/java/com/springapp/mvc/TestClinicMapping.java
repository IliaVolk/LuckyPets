package com.springapp.mvc;

import com.luckypets.config.DataSourceConfig;
import com.luckypets.config.HibernateConfig;
import com.luckypets.config.Initializer;
import com.luckypets.dao.ClinicCommentDao;
import com.luckypets.dao.ClinicDao;
import com.luckypets.dao.UserDao;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.ClinicComment;
import com.luckypets.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Initializer.class,
        HibernateConfig.class, DataSourceConfig.class})
public class TestClinicMapping {
    @Autowired
    ClinicDao clinicDao;
    @Autowired
    ClinicCommentDao clinicCommentDao;
    @Autowired
    UserDao userDao;


    //@Test
    public void testAddingCommentsToFirstClinicInList() {
        Clinic clinic = clinicDao.getClinics().get(0);
        User user = userDao.getAllUsers().get(0);
        ClinicComment clinicComment = new ClinicComment();
        clinicComment.setClinic(clinic);
        clinicComment.setMark(4);
        clinicComment.setText("good");
        clinicComment.setUser(user);
        clinicCommentDao.saveComment(clinicComment);
        testGettingClinic();


    }

    @Test
    public void testGettingClinicWithId_1() {
        Clinic clinic = clinicDao.getClinic(1);
        System.out.println(clinic);
    }

    //@Test
    public void testGettingClinic() {
        List<Clinic> clinics = clinicDao.getClinics();
        for (Clinic clinic : clinics) {
            System.out.println(clinic);
        }
    }

    //@Test
    public void testCreationClinic() {
        Clinic clinic = new Clinic();
        clinic.setTitle("First Clinic");
        clinic.setDescription("first clinic desc");
        clinic.setContactEmails(
                Arrays.asList("email1", "email2", "email3")
        );
        clinicDao.saveClinic(clinic);
    }

}

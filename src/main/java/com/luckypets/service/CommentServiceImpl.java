package com.luckypets.service;

import com.luckypets.dao.*;
import com.luckypets.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ClinicDao clinicDao;
    @Autowired
    private AdvertDao advertDao;
    @Autowired
    private ClinicCommentDao clinicCommentDao;
    @Autowired
    private AdvertCommentDao advertCommentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthenticationSupport authenticationSupport;
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveClinicComment(long clinicId, ClinicComment comment) {
        Clinic clinic = clinicDao.getClinicWithComments(clinicId);
        User user = userDao.getUser(authenticationSupport.getCurrentUserName());
        comment.setUser(user);
        comment.setClinic(clinic);
        clinicCommentDao.saveComment(comment);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAdvertComment(long advertId, AdvertComment comment) {
        Advert advert = advertDao.getAdvertWithComments(advertId);
        User user = userDao.getUser(authenticationSupport.getCurrentUserName());
        comment.setUser(user);
        comment.setAdvert(advert);
        advertCommentDao.saveComment(comment);
    }

    @Override
    public List<ClinicComment> getClinicComments(long clinicId) {
        return clinicDao.getClinicWithComments(clinicId).getComments();
    }

    @Override
    public List<AdvertComment> getAdvertComments(long advertId) {
        return advertDao.getAdvertWithComments(advertId).getAdvertComments();
    }


}

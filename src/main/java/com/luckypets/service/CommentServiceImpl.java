package com.luckypets.service;

import com.luckypets.dao.*;
import com.luckypets.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveClinicComment(long clinicId, ClinicComment comment) {
        Clinic clinic = clinicDao.getClinic(clinicId);
        User user = userDao.getUser(getCurrentUserName());
        comment.setUser(user);
        comment.setClinic(clinic);
        clinicCommentDao.saveComment(comment);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAdvertComment(long advertId, AdvertComment comment) {
        Advert advert = advertDao.getAdvert(advertId);
        User user = userDao.getUser(getCurrentUserName());
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

    private String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();

    }
}

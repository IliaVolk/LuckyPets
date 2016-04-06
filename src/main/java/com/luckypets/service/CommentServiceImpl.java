package com.luckypets.service;

import com.luckypets.dao.AdvertCommentDao;
import com.luckypets.dao.AdvertDao;
import com.luckypets.dao.ClinicCommentDao;
import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.ClinicComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveClinicComment(long clinicId, ClinicComment comment) throws BadRequestException {
        Clinic clinic = clinicDao.getClinic(clinicId);
        if (clinic == null) throw new BadRequestException(
                "Clinic with id='" + clinicId + "' does not exist");
        comment.setClinic(clinic);
        clinicCommentDao.saveComment(comment);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAdvertComment(long advertId, AdvertComment comment) throws BadRequestException {
        Advert advert = advertDao.getAdvert(advertId);
        if (advert == null) throw new BadRequestException(
                "Advert with id='" + advertId + "' does not exist"
        );
        comment.setAdvert(advert);
        advertCommentDao.saveComment(comment);
    }
}

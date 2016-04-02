package com.luckypets.dao.impl;

import com.luckypets.dao.ClinicCommentDao;
import com.luckypets.entity.ClinicComment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@Repository
public class ClinicCommentDaoImpl extends AbstractDao implements ClinicCommentDao {
    @Override
    @Transactional
    public void saveComment(ClinicComment comment) {
        getSession().saveOrUpdate(comment);
    }

    @Override
    @Transactional
    public void deleteComment(ClinicComment comment) {
        getSession().delete(comment);
    }
}

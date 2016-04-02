package com.luckypets.dao.impl;

import com.luckypets.dao.AdvertCommentDao;
import com.luckypets.entity.AdvertComment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AdvertCommentDaoImpl extends AbstractDao implements AdvertCommentDao {
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveComment(AdvertComment comment) {
        getSession().saveOrUpdate(comment);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteComment(AdvertComment comment) {
        getSession().saveOrUpdate(comment);
    }
}

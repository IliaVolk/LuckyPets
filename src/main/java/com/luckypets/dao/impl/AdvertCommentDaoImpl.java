package com.luckypets.dao.impl;

import com.luckypets.dao.AdvertCommentDao;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Repository
@Transactional
@SuppressWarnings("unchecked")
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

    @Override
    @Transactional
    public List<AdvertComment> getComments(User user, int beginIndex, int count) {
        List<AdvertComment> comments = getSession().createCriteria(AdvertComment.class).
                add(Restrictions.eq("user", user)).
                setFirstResult(beginIndex).setMaxResults(count).list();

        comments.forEach(new Consumer<AdvertComment>() {
            @Override
            public void accept(AdvertComment comment) {
                comment.getAdvert().getAnimalTypes().toString();
            }
        });

        return comments;
    }
}

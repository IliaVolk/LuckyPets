package com.luckypets.dao;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;

import java.util.List;

public interface AdvertCommentDao {
    void saveComment(AdvertComment comment);

    void deleteComment(AdvertComment comment);

    List<AdvertComment> getComments(User user, int beginIndex, int count);
}

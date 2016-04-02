package com.luckypets.dao;

import com.luckypets.entity.AdvertComment;

public interface AdvertCommentDao {
    void saveComment(AdvertComment comment);

    void deleteComment(AdvertComment comment);
}

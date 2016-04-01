package com.luckypets.dao;

import com.luckypets.entity.ClinicComment;

public interface ClinicCommentDao {
    void saveComment(ClinicComment comment);

    void deleteComment(ClinicComment comment);
}

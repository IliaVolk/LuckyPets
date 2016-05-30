package com.luckypets.service;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ClinicComment;

import java.util.List;


public interface CommentService {
    void saveClinicComment(long clinicId, ClinicComment comment);

    void saveAdvertComment(long advertId, AdvertComment comment);

    List<ClinicComment> getClinicComments(long clinicId);

    List<AdvertComment> getAdvertComments(long advertId);

}

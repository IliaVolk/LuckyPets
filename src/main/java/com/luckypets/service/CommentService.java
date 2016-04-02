package com.luckypets.service;

import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ClinicComment;

public interface CommentService {
    public void saveClinicComment(long clinicId, ClinicComment comment) throws BadRequestException;

    public void saveAdvertComment(long advertId, AdvertComment comment) throws BadRequestException;
}

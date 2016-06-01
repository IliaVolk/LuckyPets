package com.luckypets.service;


import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String login);

    void saveUser(User user);

    List<Advert> getAdverts(int beginIndex, int count);

    List<AdvertComment> getComments(int beginIndex, int count);
}

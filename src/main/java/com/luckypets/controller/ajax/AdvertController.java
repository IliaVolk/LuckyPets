package com.luckypets.controller.ajax;


import com.luckypets.dao.AdvertDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.AdvertComment;
import com.luckypets.entity.ajax.AjaxAdvertByAnimalTypeAndTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/adverts")
public class AdvertController {

    @Autowired
    private AdvertDao advertDao;

    @RequestMapping(method = RequestMethod.POST)
    public List<Advert> getAdvertsByAnimalTypeAndType(
            @RequestBody AjaxAdvertByAnimalTypeAndTypeRequest request) {
        return advertDao.getAdverts(request.getAnimalType(), request.getAdvertType(),
                request.getBeginIndex(), request.getCount());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AdvertComment> getAdvertComments(@RequestParam int advertId) {
        return advertDao.getAdvertWithComments(advertId).getAdvertComments();
    }
}

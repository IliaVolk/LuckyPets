package com.luckypets.controller.ajax;


import com.luckypets.dao.AdvertDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.ajax.AjaxAdvertByAnimalTypeAndTypeRequest;
import com.luckypets.service.ImageSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/adverts")
public class AdvertController {

    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private ImageSaver imageSaver;

    @RequestMapping(method = RequestMethod.POST)
    public List<Advert> getAdvertsByAnimalTypeAndType(
            @RequestBody AjaxAdvertByAnimalTypeAndTypeRequest request) {
        return advertDao.getAdverts(request.getAnimalType(), request.getAdvertType(),
                request.getBeginIndex(), request.getCount());
    }


    /*@RequestMapping(value = "/adverts/new", method = RequestMethod.POST)
    public String saveAdvertFromForm(
            @Valid Advert advert, BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        if (bindingResult.hasErrors()) {
            return bindingResult.toString();//back
        }

        advertDao.saveAdvert(advert);
        imageSaver.saveImage(advert.getId() + ".jpg", image, request);
        return "";//anywhere
    }


    @RequestMapping(value = "/adverts", method = RequestMethod.GET, params = "new")
    public String getNewAdvertAndReturnPageWithRegistrationForm(Model model) {
        model.addAttribute("advert", new Advert());
        return "";
    }*/

}

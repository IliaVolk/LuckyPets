package com.luckypets.controller.ajax;


import com.luckypets.dao.AdvertDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.service.ImageSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/adverts")
public class AdvertController {

    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private ImageSaver imageSaver;

    @RequestMapping(method = RequestMethod.GET,
            value = "/{animalType}/{advertType}/{beginIndex}/{count}")
    public List<Advert> getAdvertsByAnimalTypeAndType(
            @PathVariable int animalType,
            @PathVariable int advertType,
            @PathVariable int beginIndex,
            @PathVariable int count) {
        return advertDao.getAdverts(
                AnimalType.values()[animalType], AdvertType.values()[advertType], beginIndex, count
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveAdvert(@Valid Advert advert, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.toString();//back
        }
        advertDao.saveAdvert(advert);
        //imageSaver.saveImage(advert.getId() + ".jpg", image, request);
        return "";//anywhere
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

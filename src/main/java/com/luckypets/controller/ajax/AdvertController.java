package com.luckypets.controller.ajax;


import com.luckypets.dao.AdvertDao;
import com.luckypets.dao.UserDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.User;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.representation.AdvertInternationalRepresentation;
import com.luckypets.service.ImageSaver;
import com.luckypets.service.UserAuthenticationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/adverts")
public class AdvertController extends InternationalController {

    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private ImageSaver imageSaver;

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthenticationSupport authenticationSupport;
    @RequestMapping(method = RequestMethod.GET,
            value = "/{animalType}/{advertType}/{beginIndex}/{count}")
    public List<AdvertInternationalRepresentation> getAdvertsByAnimalTypeAndType(
            @PathVariable int animalType,
            @PathVariable int advertType,
            @PathVariable int beginIndex,
            @PathVariable int count,
            HttpServletRequest request) throws IOException {
        List<Advert> adverts = advertDao.getAdverts(
                AnimalType.values()[animalType], AdvertType.values()[advertType], beginIndex, count
        );
        List<AdvertInternationalRepresentation> out = prepareAdvertListToConversionToJson(adverts, request.getLocale());

        System.out.println(new String(convertObjectToJsonBytes(out)));
        return out;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{animalType}/{advertType}")
    public String saveAdvert(@RequestBody Advert advert,
                             @PathVariable int advertType,
                             @PathVariable int animalType) {

        advert.setAnimalTypes(EnumSet.of(AnimalType.values()[animalType]));
        advert.setAdvertType(AdvertType.values()[advertType]);
        User user = userDao.getUser(authenticationSupport.getCurrentUserName());
        advert.setUser(user);
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

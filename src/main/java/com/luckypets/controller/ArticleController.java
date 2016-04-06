package com.luckypets.controller;

import com.luckypets.dao.AdvertDao;
import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.LatLng;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ClinicDao clinicDao;
    @Autowired
    private AdvertDao advertDao;

    @RequestMapping(value = "/clinics/new", method = RequestMethod.POST)
    public String saveClinicFromForm(@Valid Clinic clinic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "";//return back to clinic registration page
        }
        clinicDao.saveClinic(clinic);
        return "";//return anywhere
    }

    @RequestMapping(value = "/adverts/new", method = RequestMethod.POST)
    public String saveAdvertFromForm(@Valid Advert advert, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "";//back
        }
        advertDao.saveAdvert(advert);
        return "";//anywhere
    }

    @RequestMapping(value = "/clinics", method = RequestMethod.GET)
    public String getClinics(@RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("animalType") AnimalType animalType,
                             Model model) {
        model.addAttribute("clinics", clinicDao.getClinics(beginIndex, count, animalType));
        return "articles/clinics";
    }

    @RequestMapping(value = "/clinics/around", method = RequestMethod.GET)
    public String getClinics(//TODO: better make object "ClinicRequest"
                             //TODO: or make LatLng separated
                             //TODO: and what with enum mapping?
                             @RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("animalType") AnimalType animalType,
                             //@RequestParam("latLnd") LatLng latLng,
                             @RequestParam("lat") double lat,
                             @RequestParam("lng") double lng,
                             //LatLng latLng,
                             @RequestParam("radius") double radiusInKilometers,
                             Model model) {
        LatLng latLng = new LatLng(lat, lng);
        model.addAttribute("clinics", clinicDao.getClinics(
                latLng, radiusInKilometers, animalType, beginIndex, count
        ));
        return "articles/clinics";
    }

    @RequestMapping(value = "/adverbs", method = RequestMethod.GET)
    public String getAdverbs(//TODO: better make object "AdverbRequest"
                             @RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("advertType") AdvertType type,
                             @RequestParam("animalType") AnimalType animalType,
                             Model model) {
        model.addAttribute("adverbs", advertDao.getAdverts(
                        animalType, type, beginIndex, count)
        );
        return "articles/adverbs";
    }
}

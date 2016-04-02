package com.luckypets.controller;

import com.luckypets.dao.AdvertDao;
import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.LatLng;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ClinicDao clinicDao;
    @Autowired
    private AdvertDao advertDao;

    @RequestMapping(value = "/clinic", method = RequestMethod.GET)
    public String getClinics(@RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("animalType") AnimalType animalType,
                             Model model) {
        model.addAttribute("clinics", clinicDao.getClinics(beginIndex, count, animalType));
        return "articles/clinics";
    }

    @RequestMapping(value = "/clinic/around", method = RequestMethod.GET)
    public String getClinics(@RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("animalType") AnimalType animalType,
                             @RequestParam("latLnd") LatLng latLng,
                             @RequestParam("radius") double radiusInKilometers,
                             Model model) {
        model.addAttribute("clinics", clinicDao.getClinics(
                latLng, radiusInKilometers, animalType, beginIndex, count
        ));
        return "articles/clinics";
    }

    @RequestMapping(value = "/adverb", method = RequestMethod.GET)
    public String getAdverbs(@RequestParam("beginIndex") int beginIndex,
                             @RequestParam("count") int count,
                             @RequestParam("type") AdvertType type,
                             @RequestParam("animalType") AnimalType animalType,
                             Model model) {
        model.addAttribute("adverbs", advertDao.getAdverts(
                        animalType, type, beginIndex, count)
        );
        return "articles/adverbs";
    }
}

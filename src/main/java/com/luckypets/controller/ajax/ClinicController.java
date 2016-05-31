package com.luckypets.controller.ajax;


import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.enums.District;
import com.luckypets.entity.representation.ClinicInternationalRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/clinics")
public class ClinicController extends InternationalController {

    @Autowired
    private ClinicDao clinicDao;

    @RequestMapping(method = RequestMethod.GET,
            value = "/{animalType}/{district}/{beginIndex}/{count}")
    public List<ClinicInternationalRepresentation> getClinicsByAnimalTypeAndDistrict(
            @PathVariable int animalType,
            @PathVariable int district,
            @PathVariable int beginIndex,
            @PathVariable int count,
            final HttpServletRequest httpServletRequest) {

        final List<Clinic> clinics = clinicDao.getClinics(
                beginIndex, count, AnimalType.values()[animalType], District.values()[district]
        );

        return prepareClinicListToConventionToJson(clinics, httpServletRequest.getLocale());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveClinic(@Valid Clinic clinic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.toString();//return back to clinic registration page
        }
        clinicDao.saveClinic(clinic);
        return "";//return anywhere
    }
    /*@RequestMapping(value = "/clinics/new", method = RequestMethod.POST)
    public String saveClinicFromForm(@Valid Clinic clinic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.toString();//return back to clinic registration page
        }
        clinicDao.saveClinic(clinic);
        return "";//return anywhere
    }



    @RequestMapping(value = "/adverts", method = RequestMethod.GET, params = "new")
    public String getNewAdvertAndReturnPageWithRegistrationForm(Model model) {
        model.addAttribute("advert", new Advert());
        return "";
    }*/


}

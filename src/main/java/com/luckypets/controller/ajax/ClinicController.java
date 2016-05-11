package com.luckypets.controller.ajax;


import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.ajax.AjaxClinicByAnimalTypeAndDistrictRequest;
import com.luckypets.entity.representation.ClinicInternationalRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/clinics")
public class ClinicController extends InternationalController {

    @Autowired
    private ClinicDao clinicDao;


    @RequestMapping(method = RequestMethod.POST)
    public List<ClinicInternationalRepresentation> getClinicsByAnimalTypeAndDistrict(
            @RequestBody final AjaxClinicByAnimalTypeAndDistrictRequest request,
            final HttpServletRequest httpServletRequest) {
        System.out.println(request);
        final List<Clinic> clinics = clinicDao.getClinics(request.getBeginIndex(), request.getCount(),
                request.getAnimalType(), request.getDistrict());
        return prepareClinicListToConventionToJson(clinics, httpServletRequest.getLocale());
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

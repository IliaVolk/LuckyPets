package com.luckypets.controller.ajax;


import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.ClinicComment;
import com.luckypets.entity.ajax.AjaxClinicByAnimalTypeAndDistrictRequest;
import com.luckypets.entity.representation.ClinicInternationalRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<ClinicComment> getClinicComments(@RequestParam int clinicId) {
        return clinicDao.getClinicWithComments(clinicId).getComments();
    }
}

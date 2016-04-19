package com.luckypets.controller.ajax;


import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.ClinicComment;
import com.luckypets.entity.ajax.AjaxClinicByAnimalTypeAndDistrictRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/clinics")
public class ClinicController {

    @Autowired
    private ClinicDao clinicDao;


    @RequestMapping(method = RequestMethod.POST)
    public List<Clinic> getClinicsByAnimalType(
            @RequestBody AjaxClinicByAnimalTypeAndDistrictRequest request) {
        System.out.println(request);
        return clinicDao.getClinics(request.getBeginIndex(), request.getCount(),
                request.getAnimalType(), request.getDistrict());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ClinicComment> getClinicComments(@RequestParam int clinicId) {
        return clinicDao.getClinicWithComments(clinicId).getComments();
    }
}

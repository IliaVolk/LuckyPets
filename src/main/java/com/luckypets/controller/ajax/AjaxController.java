package com.luckypets.controller.ajax;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.LatLng;
import com.luckypets.entity.ajax.AjaxClinicResponseBody;
import com.luckypets.entity.ajax.ClinicsInRadiusSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AjaxController {


    @Autowired
    private ClinicDao clinicDao;

    private static class SimpleJsonResponse {
        @JsonIgnore
        private int anInt;
        private String string;

        public int getAnInt() {
            return anInt;
        }

        public void setAnInt(int anInt) {
            this.anInt = anInt;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public SimpleJsonResponse getAjax(@RequestBody String request) {
        SimpleJsonResponse response = new SimpleJsonResponse();
        response.setAnInt(123);
        response.setString("request='" + request + "'");
        return response;
    }


    // @ResponseBody, not necessary, since class is annotated with @RestController
    // @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
    // @JsonView(Views.Public.class) - Optional, filters json data to display.
    @RequestMapping(value = "/search/api/articles", method = RequestMethod.POST)
    @ResponseBody
    public AjaxClinicResponseBody getClinicsInRadius(@RequestBody
                                                     ClinicsInRadiusSearchCriteria
                                                             request) {
        System.out.println("*************controller started");
        System.out.println("*************request=" + request);
        AjaxClinicResponseBody response = new AjaxClinicResponseBody();
        response.setClinics(
                clinicDao.getClinics(
                        new LatLng(request.getLat(), request.getLng()),
                        request.getRadiusInKilometers(), request.getAnimalType(),
                        request.getBeginIndex(), request.getCount()
                )
        );

        response.setMessage("here are clinics in radius");
        System.out.println("*************response=" + response);
        return response;
    }

}

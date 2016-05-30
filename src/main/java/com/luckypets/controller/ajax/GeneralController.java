package com.luckypets.controller.ajax;

import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.enums.District;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/ajax")
public class GeneralController extends InternationalController {


    @RequestMapping(value = "/animalList")
    public String[] getAnimalList(HttpServletRequest request) {
        //Locale locale = localeResolver.resolveLocale(request);
        return getStringArrayFromEnum(AnimalType.values(),
                request.getLocale());
        //localeResolver.resolveLocale(request), messageSource);
        ///old version
        /*AnimalType[] animalTypes = AnimalType.values();
        String[] names = new String[animalTypes.length];
        for (int i = 0; i < animalTypes.length; i++) {
            names[i] = animalTypes[i].name();
        }
        return names;*/
    }

    @RequestMapping(value = "/districtList")
    public String[] getDistrictList(HttpServletRequest request) {
        return getStringArrayFromEnum(District.values(), request.getLocale());
        //localeResolver.resolveLocale(request), messageSource);
    }

    @RequestMapping(value = "/advertTypeList")
    public String[] getAdvertTypeList(HttpServletRequest request) {
        return getStringArrayFromEnum(AdvertType.values(), request.getLocale());
    }


}

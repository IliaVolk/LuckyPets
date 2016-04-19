package com.luckypets.controller.ajax;

import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.enums.District;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ajax")
public class GeneralController {
    @RequestMapping(value = "/animalList")
    public String[] getAnimalList() {
        return getStringArrayFromEnum(AnimalType.values());
        ///old version
        /*AnimalType[] animalTypes = AnimalType.values();
        String[] names = new String[animalTypes.length];
        for (int i = 0; i < animalTypes.length; i++) {
            names[i] = animalTypes[i].name();
        }
        return names;*/
    }

    @RequestMapping(value = "/districtList")
    public String[] getDistrictList() {
        return getStringArrayFromEnum(District.values());
    }

    private static <E extends Enum<E>> String[] getStringArrayFromEnum(E[] e) {
        String[] names = new String[e.length];
        for (int i = 0; i < e.length; i++) {
            names[i] = e[i].name();
        }
        return names;
    }
}

package com.luckypets.controller.ajax;

import com.luckypets.entity.Clinic;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.representation.ClinicInternationalRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public abstract class InternationalController {
    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected LocaleResolver localeResolver;

    protected <E extends Enum<E>> String[] getStringArrayFromEnum(E[] e, Locale locale,
                                                                  MessageSource messageSource) {
        String[] names = new String[e.length];
        for (int i = 0; i < e.length; i++) {
            names[i] = messageSource.getMessage(e[i].name(), null, locale);
            //names[i] = e[i].name();
        }
        return names;
    }

    protected List<ClinicInternationalRepresentation> prepareClinicListToConventionToJson
            (List<Clinic> clinics, final Locale locale) {

        final List<ClinicInternationalRepresentation> clinicsForJson =
                new LinkedList<>();
        clinics.forEach(new Consumer<Clinic>() {
            @Override
            public void accept(Clinic c) {
                AnimalType[] animalTypeArray = new AnimalType[c.getAnimalTypes().size()];
                c.getAnimalTypes().toArray(animalTypeArray);
                ClinicInternationalRepresentation clinic =
                        new ClinicInternationalRepresentation(c,
                                getStringArrayFromEnum(
                                        animalTypeArray,
                                        locale,
                                        messageSource),
                                messageSource.getMessage(
                                        c.getDistrict().name(), null, locale)
                        );
                clinicsForJson.add(clinic);
            }
        });
        return clinicsForJson;
    }
}

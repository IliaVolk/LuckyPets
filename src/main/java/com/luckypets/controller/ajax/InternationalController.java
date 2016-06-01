package com.luckypets.controller.ajax;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypets.entity.Advert;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.representation.AdvertInternationalRepresentation;
import com.luckypets.entity.representation.ClinicInternationalRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public abstract class InternationalController {
    @Autowired
    protected MessageSource messageSource;

    /*@Autowired
    protected LocaleResolver localeResolver;
*/
    protected <E extends Enum<E>> String[] getStringArrayFromEnum(E[] e, Locale locale) {
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
                                        locale),
                                messageSource.getMessage(
                                        c.getDistrict().name(), null, locale)
                        );
                clinicsForJson.add(clinic);
            }
        });
        return clinicsForJson;
    }

    protected List<AdvertInternationalRepresentation> prepareAdvertListToConversionToJson(
            List<Advert> adverts, final Locale locale) {
        final List<AdvertInternationalRepresentation> advertsForJson =
                new LinkedList<>();

        adverts.forEach(new Consumer<Advert>() {
            @Override
            public void accept(Advert a) {
                AnimalType[] animalTypeArray = new AnimalType[a.getAnimalTypes().size()];
                a.getAnimalTypes().toArray(animalTypeArray);
                AdvertInternationalRepresentation advert =
                        new AdvertInternationalRepresentation(a,
                                getStringArrayFromEnum(animalTypeArray, locale),
                                messageSource.getMessage(
                                        a.getAdvertType().name(), null, locale)
                        );
                advertsForJson.add(advert);
            }
        });
        return advertsForJson;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}

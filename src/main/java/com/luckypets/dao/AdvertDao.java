package com.luckypets.dao;

import com.luckypets.entity.Advert;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;

import java.util.List;

public interface AdvertDao {
    List<Advert> getAdverts();

    List<Advert> getAdverts(AnimalType animalType, AdvertType advertType);

    /**
     * @param id id
     * @return fully loaded entity
     */
    Advert getAdvert(long id);

    void saveAdvert(Advert advert);

    void deleteAdvert(Advert advert);
}

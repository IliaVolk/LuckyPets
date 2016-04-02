package com.luckypets.dao;

import com.luckypets.entity.Advert;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;

import java.util.List;

public interface AdvertDao {
    List<Advert> getAdverts();

    List<Advert> getAdverts(int beginIndex, int count);

    List<Advert> getAdverts(AnimalType animalType, AdvertType advertType,
                            int beginIndex, int count);

    /**
     * @param id id
     * @return fully loaded entity
     */
    Advert getAdvertWithComments(long id);

    Advert getAdvert(long id);
    void saveAdvert(Advert advert);

    void deleteAdvert(Advert advert);
}

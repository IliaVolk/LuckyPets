package com.luckypets.dao;

import com.luckypets.entity.Clinic;
import com.luckypets.entity.LatLng;
import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.enums.District;

import java.util.List;

public interface ClinicDao {
    List<Clinic> getClinics();

    List<Clinic> getClinics(int beginIndex, int count, AnimalType animalType, District district);

    List<Clinic> getClinics(LatLng latLng, double radiusInKilometres,
                            AnimalType animalType,
                            int beginIndex, int count);
    /**
     * @param id id
     * @return fully loaded entity
     */
    Clinic getClinicWithComments(long id);

    Clinic getClinic(long id);
    void saveClinic(Clinic clinic);

    void deleteClinic(Clinic clinic);

}

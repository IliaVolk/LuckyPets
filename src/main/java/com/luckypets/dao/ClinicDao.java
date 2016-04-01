package com.luckypets.dao;

import com.luckypets.entity.Clinic;

import java.util.List;

public interface ClinicDao {
    //public List<Clinic> getClinics(LatLng latLng, double radius);
    List<Clinic> getClinics();

    Clinic getClinic(long id);

    void saveClinic(Clinic clinic);

    void deleteClinic(Clinic clinic);

}

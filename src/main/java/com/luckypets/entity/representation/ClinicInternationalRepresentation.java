package com.luckypets.entity.representation;

import com.luckypets.entity.Clinic;
import com.luckypets.entity.LatLng;

import java.util.ArrayList;
import java.util.List;

public class ClinicInternationalRepresentation {

    public ClinicInternationalRepresentation(Clinic clinic,
                                             String[] animalTypes, String district) {
        setTitle(clinic.getTitle());
        setId(clinic.getId());
        setContactEmails(new ArrayList<String>(clinic.getContactEmails()));
        setAddress(clinic.getAddress());
        setDescription(clinic.getDescription());
        setLatLng(clinic.getLatLng());
        setAnimalTypes(animalTypes);
        setDistrict(district);
    }

    private String title;
    private long id;
    private List<String> contactEmails;
    private String address;
    private String description;
    private LatLng latLng;

    private String[] animalTypes;

    private String district;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getContactEmails() {
        return contactEmails;
    }

    public void setContactEmails(List<String> contactEmails) {
        this.contactEmails = contactEmails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String[] getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(String[] animalTypes) {
        this.animalTypes = animalTypes;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

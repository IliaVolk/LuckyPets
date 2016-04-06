package com.luckypets.entity.ajax;

import com.luckypets.entity.enums.AnimalType;

public class ClinicsInRadiusSearchCriteria {
    private int beginIndex;
    private int count;

    private AnimalType animalType;
    private double lat;
    private double lng;
    private double radiusInKilometers;


    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getCount() {
        return count;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public double getRadiusInKilometers() {
        return radiusInKilometers;
    }

    public ClinicsInRadiusSearchCriteria setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
        return this;
    }

    public ClinicsInRadiusSearchCriteria setCount(int count) {
        this.count = count;
        return this;
    }

    public ClinicsInRadiusSearchCriteria setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
        return this;
    }

    public ClinicsInRadiusSearchCriteria setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public ClinicsInRadiusSearchCriteria setLng(double lng) {
        this.lng = lng;
        return this;
    }

    public ClinicsInRadiusSearchCriteria setRadiusInKilometers(double radiusInKilometers) {
        this.radiusInKilometers = radiusInKilometers;
        return this;
    }

    @Override
    public String toString() {
        return "ClinicsInRadiusSearchCriteria{" +
                "beginIndex=" + beginIndex +
                ", count=" + count +
                ", animalType=" + animalType +
                ", lat=" + lat +
                ", lng=" + lng +
                ", radiusInKilometers=" + radiusInKilometers +
                '}';
    }
}

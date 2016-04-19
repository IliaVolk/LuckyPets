package com.luckypets.entity.ajax;

import com.luckypets.entity.enums.AnimalType;
import com.luckypets.entity.enums.District;

public class AjaxClinicByAnimalTypeAndDistrictRequest {
    private int beginIndex;
    private int count;

    private AnimalType animalType;
    private District district;

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "AjaxClinicByAnimalTypeAndDistrictRequest{" +
                "beginIndex=" + beginIndex +
                ", count=" + count +
                ", animalType=" + animalType +
                ", district=" + district +
                '}';
    }
}

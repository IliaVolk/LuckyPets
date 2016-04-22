package com.luckypets.entity.ajax;

import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;

public class AjaxAdvertByAnimalTypeAndTypeRequest {
    private int beginIndex;
    private int count;

    private AnimalType animalType;
    private AdvertType advertType;


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

    public AdvertType getAdvertType() {
        return advertType;
    }

    public void setAdvertType(AdvertType advertType) {
        this.advertType = advertType;
    }

    @Override
    public String toString() {
        return "AjaxAdvertByAnimalTypeAndTypeRequest{" +
                "beginIndex=" + beginIndex +
                ", count=" + count +
                ", animalType=" + animalType +
                ", advertType=" + advertType +
                '}';
    }
}

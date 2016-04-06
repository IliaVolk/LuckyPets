package com.luckypets.entity.ajax;

import com.luckypets.entity.Clinic;

import java.io.Serializable;
import java.util.List;

public class AjaxClinicResponseBody implements Serializable {

    private List<Clinic> clinics;

    private String message;

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AjaxClinicResponseBody{" +
                "clinics=" + clinics +
                ", message='" + message + '\'' +
                '}';
    }
}

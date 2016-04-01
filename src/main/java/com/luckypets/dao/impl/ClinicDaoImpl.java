package com.luckypets.dao.impl;

import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Clinic;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ClinicDaoImpl extends AbstractDao implements ClinicDao {
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Clinic> getClinics() {
        return getSession().createCriteria(Clinic.class).list();
    }

    @Override
    @Transactional
    public Clinic getClinic(long id) {
        Clinic clinic = (Clinic) getSession().get(Clinic.class, id);
        clinic.getComments().get(0);
        clinic.getContactEmails().get(0);
        return clinic;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveClinic(Clinic clinic) {
        getSession().saveOrUpdate(clinic);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteClinic(Clinic clinic) {
        getSession().delete(clinic);
    }
}

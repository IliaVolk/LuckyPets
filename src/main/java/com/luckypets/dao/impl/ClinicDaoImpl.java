package com.luckypets.dao.impl;

import com.luckypets.dao.ClinicDao;
import com.luckypets.entity.Clinic;
import com.luckypets.entity.LatLng;
import com.luckypets.entity.enums.AnimalType;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Transactional
@Repository
public class ClinicDaoImpl extends AbstractDao implements ClinicDao {
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Clinic> getClinics() {
        return getSession().createCriteria(Clinic.class).addOrder(
                Order.desc("creationDate")
        ).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Clinic> getClinics(int beginIndex, int count, AnimalType animalType) {
        List<Clinic> clinics = getSession().createCriteria(Clinic.class).
                addOrder(Order.desc("creationDate")).list();
        Iterator<Clinic> i = clinics.iterator();
        while (i.hasNext()) {
            if (!i.next().getAnimalTypes().contains(animalType)) {
                i.remove();
            }
        }
        return Util.sublist(clinics, beginIndex, count);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Clinic> getClinics(final LatLng latLng, double radiusInKilometres,
                                   AnimalType animalType,
                                   int beginIndex, int count) {
        List<Clinic> clinics = getSession().createCriteria(Clinic.class).list();
        Iterator<Clinic> i = clinics.iterator();
        while (i.hasNext()) {
            Clinic current = i.next();
            if (latLng.distance(current.getLatLng()) > radiusInKilometres ||
                    !current.getAnimalTypes().contains(animalType)) {
                i.remove();
            }
        }
        clinics.sort(new Comparator<Clinic>() {
            @Override
            public int compare(Clinic o1, Clinic o2) {
                return (int) (o1.getLatLng().distance(latLng) -
                        o2.getLatLng().distance(latLng));
            }
        });
        return clinics.subList(beginIndex, beginIndex + count);
    }

    @Override
    @Transactional
    public Clinic getClinicWithComments(long id) {
        Clinic clinic = (Clinic) getSession().get(Clinic.class, id);
        clinic.getComments().get(0);
        clinic.getContactEmails().get(0);
        clinic.getAnimalTypes().contains(AnimalType.CAT);
        return clinic;
    }

    @Override
    @Transactional
    public Clinic getClinic(long id) {
        return (Clinic) getSession().get(Clinic.class, id);
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

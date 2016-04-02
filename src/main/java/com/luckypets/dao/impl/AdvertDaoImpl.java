package com.luckypets.dao.impl;

import com.luckypets.dao.AdvertDao;
import com.luckypets.entity.Advert;
import com.luckypets.entity.enums.AdvertType;
import com.luckypets.entity.enums.AnimalType;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Transactional
@Repository
public class AdvertDaoImpl extends AbstractDao implements AdvertDao {
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Advert> getAdverts() {
        return getSession().createCriteria(Advert.class).addOrder(
                Order.desc("creationDate")).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Advert> getAdverts(int beginIndex, int count) {
        return getSession().createCriteria(Advert.class).
                setFirstResult(beginIndex).setMaxResults(count).
                addOrder(Order.desc("creationDate")).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Advert> getAdverts(AnimalType animalType, AdvertType advertType,
                                   int beginIndex, int count) {
        List<Advert> adverts = getSession().createCriteria(Advert.class).add(
                Restrictions.eq("advertType", advertType)).addOrder(
                Order.desc("creationDate")
        ).list();
        Iterator<Advert> i = adverts.iterator();
        while (i.hasNext()) {
            if (!i.next().getAnimalTypes().contains(animalType)) {
                i.remove();
            }
        }
        return Util.sublist(adverts, beginIndex, count);
    }

    @Override
    public Advert getAdvertWithComments(long id) {
        Advert advert = (Advert) getSession().get(Advert.class, id);
        advert.getAnimalTypes().toString();
        advert.getAdvertComments().get(0);
        return advert;
    }

    @Override
    @Transactional
    public Advert getAdvert(long id) {
        return (Advert) getSession().get(Advert.class, id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAdvert(Advert advert) {
        getSession().saveOrUpdate(advert);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAdvert(Advert advert) {
        getSession().delete(advert);
    }
}

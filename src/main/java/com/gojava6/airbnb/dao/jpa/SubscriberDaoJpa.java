package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.ISubscriberDao;
import com.gojava6.airbnb.model.Subscriber;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class SubscriberDaoJpa implements ISubscriberDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createSubscriber(Subscriber subscriber) {
        em.persist(subscriber);
    }

    @Transactional
    public void deleteSubscriber(Subscriber subscriber) {
        Subscriber deletedSubscriber = em.find(Subscriber.class, subscriber.getUser().getUserId());
        em.remove(deletedSubscriber);
    }

    @Transactional
    public List<Subscriber> getSubscriberList() {
        return em.createQuery("SELECT s FROM Subscriber s").getResultList();
    }

    @Transactional
    public Subscriber getSubscriber(Integer userId) {
        return em.find(Subscriber.class, userId);
    }
}

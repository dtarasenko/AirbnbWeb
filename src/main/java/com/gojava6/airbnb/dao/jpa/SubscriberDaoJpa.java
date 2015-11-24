package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.ISubscriberDao;
import com.gojava6.airbnb.model.Subscriber;
import com.gojava6.airbnb.web.ListnerEMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class SubscriberDaoJpa implements ISubscriberDao {

    public void createSubscriber(Subscriber subscriber) {
        EntityManager em = ListnerEMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(subscriber);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
    }

    public void deleteSubscriber(Subscriber subscriber) {
        EntityManager em = ListnerEMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Subscriber deletedSubscriber = em.find(Subscriber.class, subscriber.getUser().getUserId());
                em.remove(deletedSubscriber);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
    }

    public List<Subscriber> getSubscriberList() {
        EntityManager em = ListnerEMF.createEntityManager();
        List<Subscriber> subscriberList;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                subscriberList = em.createQuery("SELECT s FROM Subscriber s").getResultList();
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return subscriberList;
    }

    public Subscriber getSubscriber(Integer userId) {
        EntityManager em = ListnerEMF.createEntityManager();
        Subscriber subscriber;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                subscriber = em.find(Subscriber.class, userId);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return subscriber;
    }
}

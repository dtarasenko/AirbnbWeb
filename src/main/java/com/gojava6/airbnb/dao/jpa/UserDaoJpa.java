package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.IUserDao;
import com.gojava6.airbnb.model.User;
import com.gojava6.airbnb.web.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDaoJpa implements IUserDao {

    public void createUser(User user) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(user);
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

    public void updateUser(User user) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                User updatedUser = em.find(User.class, user.getUserId());
                updatedUser.setName(user.getName());
                updatedUser.setSurname(user.getSurname());
                updatedUser.setEmail(user.getEmail());
                updatedUser.setUserType(user.getUserType());
                updatedUser.setPassword(user.getPassword());
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

    public void deleteUser(User user) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                User deletedUser = em.find(User.class, user.getUserId());
                em.remove(deletedUser);
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

    public List<User> getUserList() {
        EntityManager em = EMF.createEntityManager();
        List<User> userList;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                userList = em.createQuery("SELECT u FROM User u").getResultList();
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return userList;
    }

    public User getUser(Integer userId) {
        EntityManager em = EMF.createEntityManager();
        User user;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                user = em.find(User.class, userId);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return user;
    }
}

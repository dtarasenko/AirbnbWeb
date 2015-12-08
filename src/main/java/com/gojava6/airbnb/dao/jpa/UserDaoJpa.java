package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.IUserDao;
import com.gojava6.airbnb.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDaoJpa implements IUserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createUser(User user) {
        em.persist(user);
    }

    @Transactional
    public void updateUser(User user) {
        User updatedUser = em.find(User.class, user.getUserId());
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUserType(user.getUserType());
        updatedUser.setPassword(user.getPassword());
    }

    @Transactional
    public void deleteUser(User user) {
        User deletedUser = em.find(User.class, user.getUserId());
        em.remove(deletedUser);
    }

    @Transactional
    public List<User> getUserList() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Transactional
    public User getUser(Integer userId) {
        return em.find(User.class, userId);
    }
}

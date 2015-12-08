package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.IUserDao;
import com.gojava6.airbnb.model.User;
import com.gojava6.airbnb.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService  {

    @Autowired
    IUserDao iUserDao;

    public UserService() {
    }

    public User findUserByEmail(String email) {
        List<User> userList = getUserList();
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }

    public User findUserByEmailAndPassword(String email, String password) {
        List<User> userList = getUserList();
        for (User user : userList) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void createUser(String name, String surname, String email, UserType userType, String password) {
        ValidationService validationService = new ValidationService();

        if (validationService.isValidName(name) && validationService.isValidSurname(surname)
                && validationService.isValidEmail(email)) {

            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUserType(userType.getUserType());
            user.setPassword(password);
            iUserDao.createUser(user);
        }
    }

    public void deleteUser(User user) {
        iUserDao.deleteUser(user);
    }

    public void updateUserTypeToHost(User user) {
        user.setUserType(UserType.HOST.getUserType());
        iUserDao.updateUser(user);
    }

    public void updateUser(User user) {
        iUserDao.updateUser(user);
    }

    public User getUser(Integer userId) {
        return iUserDao.getUser(userId);
    }

    public List<User> getUserList() {
        return iUserDao.getUserList();
    }

}

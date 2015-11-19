//package com.gojava6.airbnb.services;
//
//
//import com.gojava6.airbnb.dao.IUserDao;
//import com.gojava6.airbnb.dao.jdbcSpring.UserJDBCTemplate;
//import com.gojava6.airbnb.model.User;
//import com.gojava6.airbnb.model.UserType;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//public class IUserDaoTest {
//
//    private UserService userService;
//    private int userCount = 2;
//    private User user;
//
//    @Before
//    public void beforeCreateUser() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//        this.userService = (UserService) context.getBean("userService");
//
//        User user = new User();
//        user.setUserId(userCount + 1);
//        user.setName("Taras");
//        user.setSurname("Shevchenko");
//        user.setEmail("taras.shevchenko@gmail.com");
//        user.setUserType("client");
//        this.user = user;
//    }
//
//    @Test
//    public void testCreateUser() {
//        userService.createUser("Taras", "Shevchenko", "taras.shevchenko@gmail.com", UserType.CLIENT);
//        assertEquals(user, userService.getUser(userCount + 1));
//    }
//
////    SET FOREIGN_KEY_CHECKS = 0;
////    TRUNCATE table1;
////    SET FOREIGN_KEY_CHECKS = 1;
//
////    @Before
////    public void beforeUpdateUser() {
////        userService.updateUserTypeToHost();
////        User user = iUserDao.getUser(userCount + 1);
////        user.setName("Vasya");
////        user.setSurname("Pupkin");
////        user.setEmail("vasya.pupkin@gmail.com");
////        user.setUserType("host");
////        this.user = user;
////    }
////
////    @Test
////    public void testUpdateUser() {
////        iUserDao.updateUser(user);
////        assertEquals(user, iUserDao.getUser(userCount + 1));
////    }
////
////    @Test
////    public void testDeleteUser() {
////        iUserDao.deleteUser(user);
////        assertNull(iUserDao.getUser(userCount + 1));
////    }
//
//}

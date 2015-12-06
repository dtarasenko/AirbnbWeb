package com.gojava6.airbnb.web.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Context implements ServletContextListener {

    private static ApplicationContext context;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        context = new ClassPathXmlApplicationContext("Beans.xml");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static ApplicationContext getContext() {
        return context;
    }
}

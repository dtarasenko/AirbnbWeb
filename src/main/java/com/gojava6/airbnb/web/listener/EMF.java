package com.gojava6.airbnb.web.listener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EMF implements ServletContextListener {

    private static EntityManagerFactory emf;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        emf = Persistence.createEntityManagerFactory("airbnb");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
}

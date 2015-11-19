package com.gojava6.airbnb.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PersistenceAppListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        PersistenceManager.getInstance().createEntityManagerFactory();
    }
}

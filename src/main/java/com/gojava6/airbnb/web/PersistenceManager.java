//package com.gojava6.airbnb.web;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.*;
//
//public class PersistenceManager {
//
//    public static final boolean DEBUG = true;
//
//    private static final PersistenceManager singleton = new PersistenceManager();
//
//    protected EntityManagerFactory emf;
//
//    public static PersistenceManager getInstance() {
//        return singleton;
//    }
//
//    public PersistenceManager() {
//    }
//
//    public EntityManagerFactory getEntityManagerFactory() {
//        if (emf == null) {
//            createEntityManagerFactory();
//        }
//        return emf;
//    }
//
//    public void closeEntityManagerFactory()     {
//        if (emf != null) {
//            emf.close();
//            emf = null;
//            if (DEBUG) {
//                System.out.println("Airbnb persistence finished at " + new java.util.Date());
//            }
//        }
//    }
//
//    protected void createEntityManagerFactory() {
//        this.emf = Persistence.createEntityManagerFactory("airbnb");
//        if (DEBUG) {
//            System.out.println("Airbnb persistence started at " + new java.util.Date());
//        }
//    }
//}

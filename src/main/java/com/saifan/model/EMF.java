package com.saifan.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ma on 2015/9/8.
 */
public class EMF {

    public static EntityManagerFactory get() {
        if (entityManagerFactory==null){
            entityManagerFactory= Persistence.createEntityManagerFactory("saifan");
        }
        return entityManagerFactory;
    }

    static EntityManagerFactory entityManagerFactory ;

}

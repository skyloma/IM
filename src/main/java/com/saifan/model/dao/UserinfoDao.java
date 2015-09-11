package com.saifan.model.dao;

import com.saifan.model.EMF;
import com.saifan.model.Userinfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ma on 2015/9/9.
 */

public class UserinfoDao {


    public  void  save(Userinfo userinfo){
        EntityManager entityManager = EMF.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userinfo);
        entityManager.getTransaction().commit();
        entityManager.close();

    }



}

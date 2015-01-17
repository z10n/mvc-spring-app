/*
 * Copyright (C) 2014 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 020 20.06.2014
 */
package by.it.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class BaseDao<T> implements Dao<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;

    public BaseDao() {
    }

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession(){
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
        }
        catch (HibernateException ex) {
            log.debug("Could not retrieve pre-bound Hibernate session", ex);
        }
        if (session == null) {
            session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.MANUAL);
        }

        return session;
    }

    @Override
    public T add(T t) {
        getSession().save(t);
        log.info("Save:" + t);
        return (T) t;
    }

    @Override
    public void update(T t) {
        getSession().update(t);
        log.info("Update:" + t);
    }

    @Override
    public T get(Class<T> clazz, Serializable id) {
        log.info("Get:" + id);
        return (T) getSession().load(clazz, id);
    }

    @Override
    public void delete(T t) {
        log.info("Delete:" + t);
        getSession().delete(t);
    }

    @Override
    public void refresh(T t) {
        log.info("Refresh:" + t);
        getSession().refresh(t);
    }

}

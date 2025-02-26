package com.example.factory;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@Slf4j
@Getter
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {}


    private static SessionFactory buildSessionFactory(){
        try {
            Configuration configuration = new Configuration().configure();
            return configuration.buildSessionFactory();
        } catch (HibernateException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

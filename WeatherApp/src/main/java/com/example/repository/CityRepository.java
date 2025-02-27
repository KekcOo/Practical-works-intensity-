package com.example.repository;


import com.example.entity.WeatherEntity;
import com.example.factory.HibernateUtil;
import com.example.mapper.MapperWeather;
import com.example.response.WeatherResponse;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

@Getter
@Log4j2
public class CityRepository {

    public void insertDataHibernate(WeatherResponse weather) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(MapperWeather.responseToEntity(weather));
            session.getTransaction().commit();
        }

    }


    public Optional<WeatherEntity> findByNameHibernate(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<WeatherEntity> query = session.createQuery("""
                    SELECT w\s
                    FROM WeatherEntity w\s
                    WHERE w.cityName = :cityName
                   \s""", WeatherEntity.class);
            query.setParameter("cityName", name);
            return query.uniqueResultOptional();
        }
    }


}

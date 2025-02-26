package com.example.repository;


import com.example.entity.WeatherEntity;
import com.example.factory.Factory;
import com.example.factory.HibernateUtil;
import com.example.mapper.DtoWeather;
import com.example.mapper.MapperWeather;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.Optional;

@Getter
@Log4j2
public class CityRepository {


    public void insertDataHibernate(DtoWeather weather) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(MapperWeather.dtoToEntity(weather));
            session.getTransaction().commit();
        }

    }

    public Optional<WeatherEntity> findByNameHibernate(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<WeatherEntity> query = session.createQuery("""
                    SELECT w 
                    FROM WeatherEntity w 
                    WHERE w.cityName = :cityName
                    """, WeatherEntity.class);
            query.setParameter("cityName", name);
            return query.uniqueResultOptional();
        }
    }

    public DtoWeather findByName(String cityName) {
        String selectSQL = "SELECT * FROM weather_data WHERE city_name = ?";
        DtoWeather weather = new DtoWeather();
        try (Connection connection = Factory.open();
             PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                log.info("Город не найден.");
                return null;
            }

            do {
                weather.setCityName(resultSet.getString("city_name"));
                weather.setTemperature(resultSet.getDouble("temperature"));
                weather.setDescription(resultSet.getString("description"));
                weather.setHumidity(resultSet.getInt("humidity"));
                weather.setPressure(resultSet.getInt("pressure"));
            } while (resultSet.next());


        } catch (SQLException e) {
            log.info("Ошибка в CityRepository->findByName{}", e.getMessage());
        }
        return weather;
    }
}

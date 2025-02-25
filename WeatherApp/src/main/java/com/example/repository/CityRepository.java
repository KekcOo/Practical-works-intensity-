package com.example.repository;


import com.example.factory.Factory;
import com.example.mapper.DtoWeather;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Getter
@Log4j2
public class CityRepository {



    public void insertData(DtoWeather weather) {
        String insertSQL = "INSERT INTO weather_data (city_name, temperature, description, humidity, pressure) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Factory.open();
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            statement.setString(1, weather.getCityName());
            statement.setDouble(2, weather.getTemperature());
            statement.setString(3, weather.getDescription());
            statement.setInt(4, weather.getHumidity());
            statement.setInt(5, weather.getPressure());

            statement.executeUpdate();
            log.info("Данные о погоде успешно добавлены!");

        } catch (SQLException e) {
            log.info("Ошибка в CityRepository->insertData{}", e.getMessage());
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

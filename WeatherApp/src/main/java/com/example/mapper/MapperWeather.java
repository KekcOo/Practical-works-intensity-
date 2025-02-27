package com.example.mapper;

import com.example.entity.WeatherEntity;
import com.example.model.Main;
import com.example.model.Weather;
import com.example.response.WeatherResponse;

import java.time.Instant;
import java.time.LocalDateTime;

public class MapperWeather {

    public static WeatherResponse dtoToResponse(WeatherEntity entity) {
        WeatherResponse response = new WeatherResponse();
        Main main = new Main();
        main.setHumidity(entity.getHumidity());
        main.setPressure(entity.getPressure());
        main.setTemp(entity.getTemperature());
        response.setMain(main);
        response.setName(entity.getCityName());
        Weather weather = new Weather();
        weather.setDescription(entity.getDescription());
        response.setWeather(new Weather[]{weather});
        return response;
    }

    public static WeatherEntity responseToEntity(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCityName(response.getName());
        entity.setTemperature(response.getMain().getTemp());
        entity.setDescription(response.getWeather()[0].getDescription());
        entity.setHumidity(response.getMain().getHumidity());
        entity.setPressure(response.getMain().getPressure());
        return entity;
    }





}

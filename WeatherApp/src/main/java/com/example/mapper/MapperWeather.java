package com.example.mapper;

import com.example.entity.WeatherEntity;
import com.example.model.Main;
import com.example.model.Weather;
import com.example.response.WeatherResponse;

public class MapperWeather {
    public static DtoWeather entityTiDto(WeatherEntity entity){
        DtoWeather dto = new DtoWeather();
        dto.setCityName(entity.getCityName());
        dto.setTemperature(entity.getTemperature());
        dto.setDescription(entity.getDescription());
        dto.setHumidity(entity.getHumidity());
        dto.setPressure(entity.getPressure());
        return dto;
    }
    public static WeatherEntity dtoToEntity(DtoWeather dto){
        WeatherEntity entity =new WeatherEntity();
        entity.setCityName(dto.getCityName());
        entity.setTemperature(dto.getTemperature());
        entity.setDescription(dto.getDescription());
        entity.setHumidity(dto.getHumidity());
        entity.setPressure(dto.getPressure());
        return entity;
    }

    public static DtoWeather responseToDto(WeatherResponse response) {
        DtoWeather dto = new DtoWeather();
        dto.setCityName(response.getName());
        dto.setTemperature(response.getMain().getTemp());
        dto.setDescription(response.getWeather()[0].getDescription());
        dto.setHumidity(response.getMain().getHumidity());
        dto.setPressure(response.getMain().getPressure());
        return dto;
    }

    public static WeatherResponse dtoToResponse(DtoWeather dtoWeather) {
        WeatherResponse response = new WeatherResponse();

        Main main = new Main();
        main.setHumidity(dtoWeather.getHumidity());
        main.setPressure(dtoWeather.getPressure());
        main.setTemp(dtoWeather.getTemperature());
        response.setMain(main);

        response.setName(dtoWeather.getCityName());

        Weather weather = new Weather();
        weather.setDescription(dtoWeather.getDescription());
        response.setWeather(new Weather[]{weather});
        return response;
    }


}

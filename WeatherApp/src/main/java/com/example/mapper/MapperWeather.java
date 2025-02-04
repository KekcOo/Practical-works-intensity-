package com.example.mapper;

import com.example.model.Main;
import com.example.model.Weather;
import com.example.response.WeatherResponse;

public class MapperWeather {

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

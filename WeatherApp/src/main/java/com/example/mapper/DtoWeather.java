package com.example.mapper;

import lombok.Data;

@Data
public class DtoWeather {

    private String cityName;
    private double temperature;
    private String description;
    private int humidity;
    private int pressure;
}

package com.example.response;


import com.example.model.Main;
import com.example.model.Weather;
import lombok.Getter;

@Getter
public class WeatherResponse {

    private Main main;
    private Weather[] weather;
    private String name;

}

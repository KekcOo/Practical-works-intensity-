package com.example.itil;


import com.example.factory.Factory;
import com.example.repository.CityRepository;

public class CheckInput {
    private static final CityRepository cityRepository = Factory.getRepository();
    public static void checkInput(String text){
        String[] strings = text.split(" ");
        switch (strings[0]){
            case "find": WeatherApp.getWeather(strings[1]);
            break;
            case "all" : cityRepository.findAll();
            break;
        }
    }
}

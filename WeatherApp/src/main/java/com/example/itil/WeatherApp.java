package com.example.itil;


import com.example.entity.WeatherEntity;
import com.example.factory.Factory;

import com.example.mapper.MapperWeather;
import com.example.repository.CityRepository;
import com.example.response.WeatherResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class WeatherApp {
    private static final String API_KEY = "f58eff2c05c09165deca1fe1d5b87d01";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final CityRepository cityRepository = Factory.getRepository();

    public static WeatherEntity getWeather(String city) {

        String output = city.substring(0, 1).toUpperCase() + city.substring(1);
        Optional<WeatherEntity> entity = cityRepository.findByNameHibernate(output);
        if (entity.isPresent()) {
//            long hoursBetween = Math.abs(ChronoUnit.HOURS.between(entity.get().getTime(), LocalDateTime.now()));
//            if(hoursBetween<=1){
                return entity.get();
        }
        try {
            WeatherResponse weatherResponse = fetchWeatherFromApi(output);
            if (weatherResponse == null) {
                log.error("Не удалось получить погоду для города: {}", output);
                return null;
            }


            cityRepository.insertDataHibernate(weatherResponse);

            return MapperWeather.responseToEntity(weatherResponse);

        } catch (IOException e) {
            log.error("Ошибка при выполнении запроса к API: {}", e.getMessage(), e);
            return null;
        }
    }

    private static WeatherResponse fetchWeatherFromApi(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("%s?q=%s&appid=%s&units=metric&lang=ru", BASE_URL, city, API_KEY);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("Ошибка запроса к API: HTTP Code {}", response.code());
                return null;
            }

            String responseBody = Objects.requireNonNull(response.body()).string();
            Gson gson = new Gson();
            return gson.fromJson(responseBody, WeatherResponse.class);
        }
    }


}

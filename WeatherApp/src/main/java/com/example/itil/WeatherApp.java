package com.example.itil;


import com.example.response.WeatherResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class WeatherApp {
    private static final String API_KEY = "f58eff2c05c09165deca1fe1d5b87d01";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void getWeather(String city) {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?q=%s&appid=%s&units=metric&lang=ru", BASE_URL, city, API_KEY);


        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.info("Ошибка запроса: {}", response.body());
                return;
            }

            String responseBody = Objects.requireNonNull(response.body()).string();
            Gson gson = new Gson();
            WeatherResponse weatherResponse = gson.fromJson(responseBody, WeatherResponse.class);


            printWeatherInfo(weatherResponse);
        } catch (IOException e) {
            log.info("Ошибка выполнения запроса: {}", e.getMessage());

        }


    }
    private static void printWeatherInfo(WeatherResponse weatherResponse) {
        if (weatherResponse == null || weatherResponse.getMain() == null) {
            log.info("Не удалось получить данные о погоде.");
            return;
        }
        log.info("Город: {}", weatherResponse.getName());
        log.info("Температура: {} °C", weatherResponse.getMain().getTemp());
        log.info("Описание: {}", weatherResponse.getWeather()[0].getDescription());
        log.info("Влажность: {}%", weatherResponse.getMain().getHumidity());
        log.info("Давление: {} гПа", weatherResponse.getMain().getPressure());

    }
}

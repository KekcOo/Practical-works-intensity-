package com.example;

import com.example.itil.InitDb;
import com.example.itil.WeatherApp;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@Slf4j
public class Main {
    public static void main(String[] args) {


        InitDb.createTable();

        Scanner scanner = new Scanner(System.in);

        String input = "";
        log.info("Введите город ");
        log.info("Пример: Москва ");

        while (!input.equals("exit")) {
            input = scanner.nextLine().toLowerCase().trim();
            WeatherApp.getWeather(input);

        }

    }
}
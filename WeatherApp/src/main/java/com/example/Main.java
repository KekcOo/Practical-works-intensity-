package com.example;

import com.example.itil.WeatherApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            input = scanner.nextLine().toLowerCase().trim();
            WeatherApp.getWeather(input);

        }

    }
}
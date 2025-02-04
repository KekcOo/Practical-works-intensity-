package com.example.itil;


import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class InitDb {
    //TODO вынести в application.yml
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/weatherdb";
    private static final String DB_USER = "root";
    private static final String PASSWORD = "root";

    public static void createTable() {

        String createTableSQL = """
                   create table IF NOT EXISTS weather_data
                  (id BIGSERIAL PRIMARY KEY,
                     city_name VARCHAR(100) NOT NULL UNIQUE,
                    temperature DECIMAL(5, 2) NOT NULL,
                    description VARCHAR(255) NOT NULL,
                    humidity INT NOT NULL,
                    pressure INT NOT NULL )
                """;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(createTableSQL);
        } catch (Exception e) {
            log.info("Ошибка с соединением с базой {}", String.valueOf(e));
        }

    }



}

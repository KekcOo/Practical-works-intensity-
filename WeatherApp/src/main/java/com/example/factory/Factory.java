package com.example.factory;


import com.example.repository.CityRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Factory {
    private static final String KEY_URL = "db_url";
    private static final String KEY_USER = "db_user";
    private static final String KEY_PASSWORD = "db_pwd";
    private static CityRepository repository;
    private Factory() {
    }
    public static synchronized CityRepository getRepository() {
        if (repository == null) {
            repository = new CityRepository();
        }
        return repository;
    }
    public static Connection open(){
        try {
            return DriverManager.getConnection(PropertiesUtil.getProperty(KEY_URL)
                    ,PropertiesUtil.getProperty(KEY_USER)
                    ,PropertiesUtil.getProperty(KEY_PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

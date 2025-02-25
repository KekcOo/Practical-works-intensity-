package com.example.factory;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    private final static Properties PROPERTIES = new Properties();
    static {
        loadProperties();
    }

    private static void loadProperties() {
       var inputStream = PropertiesUtil.class.getClassLoader()
               .getResourceAsStream("application.yml");
        try {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getProperty(String key) {
    return PROPERTIES.getProperty(key);
    }
}

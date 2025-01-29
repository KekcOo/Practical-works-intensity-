package com.example.itil;


import com.example.factory.Factory;
import com.example.model.City;
import com.example.repository.CityRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class ReaderJsonCity {
    private static final CityRepository cityRepository = Factory.getRepository();
    public static void readJson(){

        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/city.list.json")) {
            Type cityListType = new TypeToken<List<City>>() {}.getType();
            List<City> cities = gson.fromJson(reader, cityListType);
            for (City city : cities) {
                cityRepository.save(city);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

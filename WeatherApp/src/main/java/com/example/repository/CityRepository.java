package com.example.repository;


import com.example.model.City;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Getter
@Log4j2
public class CityRepository {

    private final Map<String, City> cityMap = new HashMap<>();

    public void save(City city) {
        cityMap.put(city.getName(), city);
    }


    public void findAll() {
        for (Map.Entry entry : cityMap.entrySet()) {
            log.info("[Key]: {}[Value]: {}", entry.getKey(), entry.getValue());
        }
    }


}

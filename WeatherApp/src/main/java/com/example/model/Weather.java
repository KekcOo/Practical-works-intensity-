package com.example.model;

import lombok.Data;

import java.util.Objects;


@Data
public class Weather {
    private String description;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(description, weather.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }
}

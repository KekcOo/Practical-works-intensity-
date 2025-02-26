package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather_data")
public class WeatherEntity {
    @Id
    private Integer id;

    @Column(name = "city_name")
    private String cityName;

    private Double temperature;
    private String description;
    private Integer humidity;
    private Integer pressure;
}

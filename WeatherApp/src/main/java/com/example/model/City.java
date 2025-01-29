package com.example.model;

import lombok.Getter;

@Getter
public class City {
    private long id;
    private String name;
    private String country;
    private Coord coord;
}

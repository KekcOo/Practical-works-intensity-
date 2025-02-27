package com.example.factory;


import com.example.repository.CityRepository;


public final class Factory {
    private static CityRepository repository;

    private Factory() {
    }

    public static synchronized CityRepository getRepository() {
        if (repository == null) {
            repository = new CityRepository();
        }
        return repository;
    }

}

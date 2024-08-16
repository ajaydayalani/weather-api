package com.ajay.hackerrank.weather.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.ajay.hackerrank.weather.model.Weather;
import java.util.List;
import java.time.LocalDate;


public interface WeatherRepo extends ListCrudRepository<Weather,Integer>{
    public List<Weather> findAllByDate(LocalDate date);
}

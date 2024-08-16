package com.ajay.hackerrank.weather.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import com.ajay.hackerrank.weather.model.Weather;


@SpringBootTest
public class WeatherRepoTests {

    @Autowired
   private  WeatherRepo weatherRepo;

   @Test
    public void Weather_Save_ReturnsWeather(){
        Weather weather = new Weather(null,LocalDate.now(),(float) 3.55,(float) 4.55,"orlando","florida",new ArrayList<>());
        
        Weather savedWeather = weatherRepo.save(weather);

        Assertions.assertThat(savedWeather).isNotNull();
        Assertions.assertThat(savedWeather.id()).isGreaterThan(0);
    }
    
}

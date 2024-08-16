package com.ajay.hackerrank.weather.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.hackerrank.weather.model.Weather;
import com.ajay.hackerrank.weather.service.WeatherService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;


    @GetMapping({"/",""})
    public ResponseEntity<?> getAll(@RequestParam(required = false) String order, @RequestParam(required=false) String city, 
    @RequestParam(required =  false) LocalDate date) {
        List<Weather> weather= weatherService.getFiltered(order,city, date);
        if (weather==null){
            return new ResponseEntity<>("Invalid Parameters",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(weather,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Weather> addEntry(@Valid @RequestBody Weather weather) {
        return new ResponseEntity<>(weatherService.add(weather),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Integer id) {
        Weather weather =  weatherService.getById(id);
        if(weather==null){
            return new ResponseEntity<>("Entry Not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(weather,HttpStatus.OK);
    }
    

    
    
    
    
}

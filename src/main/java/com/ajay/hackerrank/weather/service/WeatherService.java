package com.ajay.hackerrank.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.hackerrank.weather.repository.WeatherRepo;
import com.ajay.hackerrank.weather.model.Weather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo; 
    

    public Weather add(Weather weather){
        Weather toAdd  = new Weather(
            null,
            weather.date(),
            weather.lat(),
            weather.lon(),
            weather.city().toLowerCase(),
            weather.state(),
            weather.temperatures());
        return weatherRepo.save(toAdd);
    }

    public List<Weather> getAll(){
        return weatherRepo.findAll();
    }


    public Weather getById(Integer id){
        return weatherRepo.findById(id).orElse(null);
    }

  

    public List<Weather> getFiltered(String order, String city, LocalDate date) {
        List<Weather> res= new ArrayList<>();
        if(date!=null){
            res.addAll(weatherRepo.findAllByDate(date));

        }else{
            res.addAll(weatherRepo.findAll());
        
        }


        if(city!=null){
        Set<String> cities = new HashSet<String>(
            Arrays.asList(city.split(","))
            .stream()
            .map(x->x.toLowerCase())
            .collect(Collectors.toList())
            ); 
            res= res.stream().filter(x->cities.contains(x.city())).collect(Collectors.toList());
        }

        Comparator<Weather> comparator = (w1, w2) -> {
            return Integer.valueOf(w1.id()).compareTo(w2.id());
        };

        if(order!=null){
            if(order.equals("date")){

                comparator = (w1, w2) -> {
                    int dateComparison = w1.date().compareTo(w2.date());
                    if (dateComparison != 0) {
                        return dateComparison;
                    }
                    return Integer.compare(w1.id(), w2.id()); 
                }; 
                                  
            }else if (order.equals("-date")){
                comparator = (w1, w2) -> {
                    int dateComparison = w2.date().compareTo(w1.date());
                    if (dateComparison != 0) {
                        return dateComparison;
                    }
                    return Integer.compare(w1.id(), w2.id()); };              
            }
        }

        res.sort(comparator);


        
        return res;
    
    }
}

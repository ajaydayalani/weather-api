package com.ajay.hackerrank.weather.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

public record Weather(
    @Id
    Integer id,
    
    @DateTimeFormat
    LocalDate date,

    Float lat,

    Float lon,

    @NotBlank
    String city,

    @NotBlank
    String state,

    
    List<Double> temperatures
) {}


 
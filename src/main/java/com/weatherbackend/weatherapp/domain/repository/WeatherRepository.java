package com.weatherbackend.weatherapp.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.weatherbackend.weatherapp.domain.model.Weather;

//This is CRUD-repository for holding weather records. 

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

}

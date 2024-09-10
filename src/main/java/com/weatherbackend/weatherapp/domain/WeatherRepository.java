package com.weatherbackend.weatherapp.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//This is CRUD-repository for holding weather records. 

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

}

package com.weatherbackend.weatherapp.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    Page<Weather> findAllByOrderByDateDescTimeDesc(Pageable pageable);
    Weather findFirstByOrderByDateAscTimeAsc();
}

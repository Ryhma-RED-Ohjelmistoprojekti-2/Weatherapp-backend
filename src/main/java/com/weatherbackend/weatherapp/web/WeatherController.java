package com.weatherbackend.weatherapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.weatherbackend.weatherapp.domain.WeatherRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class WeatherController {
    

    // weather repository
    @Autowired
    WeatherRepository weatherRepository;


    // main page
    @GetMapping("/index")
    public String weatherPage(Model model) {
        
        model.addAttribute("weathers", weatherRepository.findAll());
        return "index";
    }
    

}

// tän voi poistaa jos ei tuu tarvetta

package com.weatherbackend.weatherapp.domain;

import jakarta.persistence.*;

@Entity(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float temperature;

    private Integer humidity;

    @Column(name = "barometric_pressure")
    private Float barometricPressure;

    @Column(name = "wind_direction")
    private Integer windDirection;

    @Column(name = "avg_wind_speed")
    private Float avgWindSpeed;

    @Column(name = "max_wind_speed")
    private Float maxWindSpeed;

    @Column(name = "rainfall_one_hour")
    private Float rainfallOneHour;

    @Column(name = "rainfall_twenty_four_hour")
    private Float rainfallTwentyFourHour;

    private String date;

    private String time;

    public Weather() {
    }

    public Weather(
            Float temperature,
            Integer humidity,
            Float barometricPressure,
            Integer windDirection,
            Float avgWindSpeed, Float maxWindSpeed, Float rainfallOneHour, Float rainfallTwentyFourHour, String date,
            String time) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.barometricPressure = barometricPressure;
        this.windDirection = windDirection;
        this.avgWindSpeed = avgWindSpeed;
        this.maxWindSpeed = maxWindSpeed;
        this.rainfallOneHour = rainfallOneHour;
        this.rainfallTwentyFourHour = rainfallTwentyFourHour;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getBarometricPressure() {
        return barometricPressure;
    }

    public void setBarometricPressure(Float barometricPressure) {
        this.barometricPressure = barometricPressure;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public Float getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(Float avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public Float getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(Float maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public Float getRainfallOneHour() {
        return rainfallOneHour;
    }

    public void setRainfallOneHour(Float rainfallOneHour) {
        this.rainfallOneHour = rainfallOneHour;
    }

    public Float getRainfallTwentyFourHour() {
        return rainfallTwentyFourHour;
    }

    public void setRainfallTwentyFourHour(Float rainfallTwentyFourHour) {
        this.rainfallTwentyFourHour = rainfallTwentyFourHour;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Weather [temperature=" + temperature + ", humidity=" + humidity + ", barometricPressure="
            + barometricPressure + ", windDirection=" + windDirection + ", avgWindSpeed=" + avgWindSpeed
            + ", maxWindSpeed=" + maxWindSpeed + ", rainfallOneHour=" + rainfallOneHour
            + ", rainfallTwentyFourHour=" + rainfallTwentyFourHour + ", date=" + date + ", time=" + time + "]"
        ;
    }
}
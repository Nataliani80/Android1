package com.example.natali.android1;

public class WeatherForecast {

    private String forecastDate;
    private int forecastTemperature;
    private String forecastDescription;
    private int forecastProbabilityOfPrecipitation;
    private Double forecastSpeedWind;
    private String forecastWindDirection;


    public WeatherForecast(String forecastDate,
                           int forecastTemperature,
                           String forecastDescription,
                           int forecastProbabilityOfPrecipitation,
                           Double forecastSpeedWind,
                           String forecastWindDirection) {
        this.forecastDate = forecastDate;
        this.forecastTemperature = forecastTemperature;
        this.forecastDescription = forecastDescription;
        this.forecastProbabilityOfPrecipitation = forecastProbabilityOfPrecipitation;
        this.forecastSpeedWind = forecastSpeedWind;
        this.forecastWindDirection = forecastWindDirection;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public int getForecastTemperature() {
        return forecastTemperature;
    }

    public String getForecastDescription() {
        return forecastDescription;
    }

    public int getForecastProbabilityOfPrecipitation() {
        return forecastProbabilityOfPrecipitation;
    }

    public Double getForecastSpeedWind() {
        return forecastSpeedWind;
    }

    public String getForecastWindDirection() {
        return forecastWindDirection;
    }
}

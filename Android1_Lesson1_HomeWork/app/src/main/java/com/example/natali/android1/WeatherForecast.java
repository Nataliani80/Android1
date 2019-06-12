package com.example.natali.android1;

public class WeatherForecast {

    private String forecastDate;
    private int forecastTemperature;
    private String forecastDescription;
    private int forecastProbabilityOfPrecipitation;
    private Double forecastWindSpeed;
    private String forecastWindDirection;
    private long id;
    private long cityID;

    public WeatherForecast(String forecastDate,
                           int forecastTemperature,
                           String forecastDescription,
                           int forecastProbabilityOfPrecipitation,
                           Double forecastWindSpeed,
                           String forecastWindDirection) {
        this.forecastDate = forecastDate;
        this.forecastTemperature = forecastTemperature;
        this.forecastDescription = forecastDescription;
        this.forecastProbabilityOfPrecipitation = forecastProbabilityOfPrecipitation;
        this.forecastWindSpeed = forecastWindSpeed;
        this.forecastWindDirection = forecastWindDirection;
    }

    public WeatherForecast() {
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

    public Double getForecastWindSpeed() {
        return forecastWindSpeed;
    }

    public String getForecastWindDirection() {
        return forecastWindDirection;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public void setForecastDescription(String forecastDescription) {
        this.forecastDescription = forecastDescription;
    }

    public void setForecastProbabilityOfPrecipitation(int forecastProbabilityOfPrecipitation) {
        this.forecastProbabilityOfPrecipitation = forecastProbabilityOfPrecipitation;
    }

    public void setForecastWindSpeed(Double forecastWindSpeed) {
        this.forecastWindSpeed = forecastWindSpeed;
    }

    public void setForecastWindDirection(String forecastWindDirection) {
        this.forecastWindDirection = forecastWindDirection;
    }

    public void setForecastTemperature(int forecastTemperature) {
        this.forecastTemperature = forecastTemperature;
    }

    public void setIdWeather(long id) {
        this.id = id;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }
}

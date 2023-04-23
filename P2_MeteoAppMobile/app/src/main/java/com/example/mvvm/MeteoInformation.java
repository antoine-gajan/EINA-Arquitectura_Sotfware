package com.example.mvvm;

public class MeteoInformation {
    // Attributes
    private String city;
    private double temperature;
    private String weather;
    private double wind;
    private double rain;

    // Constructor
    public MeteoInformation(String city, double temperature, double wind, double rain) {
        this.city = city;
        this.temperature = temperature;
        this.wind = wind;
        this.rain = rain;
    }
    // Getter
    public String get_city() {return this.city;}
    public double get_temperature() {return this.temperature;}
    public double get_wind() {return this.wind;}
    public double get_rain() {return this.rain;}
}

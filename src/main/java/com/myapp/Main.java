package com.myapp;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args){
        String urlString = "http://api.weatherapi.com/v1/current.json?key=2cd652eb743648b49e4140126242603&q=Tokyo&aqi=yes";

        String json = WeatherApiRequest.getWeatherData(urlString);
        Gson gson = new Gson();

        WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

        System.out.println("com.myapp.Location:"+response.location.name+","+response.location.country);
        System.out.println("Temperature:"+response.current.temp_c+"℃");
        System.out.println("com.myapp.Condition:"+response.current.condition.text);
        System.out.println("Wind Speed"+response.current.wind_kph+"kph");
    }
}

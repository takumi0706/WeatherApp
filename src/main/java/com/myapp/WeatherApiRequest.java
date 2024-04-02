package com.myapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiRequest {

    public static String getWeatherData( String urlString ){
        StringBuilder response = new StringBuilder();

        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) !=null){
                response.append(inputLine);
            }
            in.close();

        }catch (Exception e){
        }
        return response.toString();
    }
}

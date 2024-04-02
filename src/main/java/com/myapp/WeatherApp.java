package com.myapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.google.gson.Gson;

public class WeatherApp extends Application {
    @Override
    public void start(Stage primaryStage){
        TextField cityTextField = new TextField();
        cityTextField.setPromptText("都市名を入力");

        Button searchButton = new Button("検索");
        TextArea weatherInfoArea = new TextArea();
        weatherInfoArea.setEditable(false);

        searchButton.setOnAction(event ->{
            String city = cityTextField.getText();
            updateWeatherInfo(city, weatherInfoArea);
        });

        VBox root = new VBox(10,new Label("都市名:"), cityTextField, searchButton, weatherInfoArea);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("天気予報アプリ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateWeatherInfo(String city, TextArea weatherInfoArea){
        String apikey = "your_API";
        String urlString = String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no", apikey,city);

        String json = WeatherApiRequest.getWeatherData(urlString);
        Gson gson = new Gson();

        try{
            WeatherResponse response = gson.fromJson(json, WeatherResponse.class);
            String info = String.format("場所：%s, %s\n温度：%s ℃\n天気：%s\n風速：%s kph",response.location.name,response.location.country,response.current.temp_c, response.current.condition.text, response.current.wind_kph);
            weatherInfoArea.setText(info);
        }catch (Exception e){
            e.printStackTrace();
            weatherInfoArea.setText("天気情報を取得できませんでした。");
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}

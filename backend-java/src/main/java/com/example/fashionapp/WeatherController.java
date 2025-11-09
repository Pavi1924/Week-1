package com.example.fashionapp;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) {
        String apiKey = System.getenv("OPENWEATHER_API_KEY"); // Load from .env or environment variable
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                     "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject obj = new JSONObject(response);
        double temp = obj.getJSONObject("main").getDouble("temp");
        String condition = obj.getJSONArray("weather").getJSONObject(0).getString("main");

        return "Weather in " + city + ": " + temp + "°C, " + condition;
    }
}

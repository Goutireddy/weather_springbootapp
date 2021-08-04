package com.weather.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dharanidar Goutireddy
 * Created on 03-08-2021
 **/
@Service
public class WeatherService {

    private static final String RAPID_URL = "https://weatherapi-com.p.rapidapi.com/current.json?q=";

    public ResponseEntity<String> findWeather(final String searchText) {
        RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-host", "weatherapi-com.p.rapidapi.com");
        headers.set("x-rapidapi-key", "5df5b0fb32mshaee1296db2600c9p123543jsn95466f43a2b3");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            return restTemplate.exchange(RAPID_URL + searchText, HttpMethod.GET, requestEntity, String.class);
    }

}

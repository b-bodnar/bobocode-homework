package com.bobocode.http.hw_11;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class RestTemplateHW {
    private static final String LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        var photos = restTemplate.getForObject(LINK, Photos.class);
        photos.getListPhotos()
                .forEach(photo -> System.out.println(photo.getImageName()));
    }
}

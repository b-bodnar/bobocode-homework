package com.bobocode.http.hw_11;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpClientHM {
    private static final String LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LINK))
                .GET()          // http method GET
                .build();       // create instance

        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        var photos = mapper.readValue(response.body(), Photos.class);
        photos.getPhotos().forEach(photo -> System.out.println(photo.getImageName()));
    }
}


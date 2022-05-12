package com.bobocode.training;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Training {

    private static final String LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";

    @SneakyThrows
    public static void main(String[] args) {

        var request = HttpRequest.newBuilder()
                .uri(URI.create(LINK))
                .version(HttpClient.Version.HTTP_2)
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print all headers
        response.headers()
                .map().forEach((k, v) -> System.out.println(k + ": " + v));

        //find and print specific header 'content-type'
        response.headers()
                .firstValue("content-type")
                .ifPresent(System.out::println);

        // get protocol version
        var version = response.version();
        System.out.println(version);

    }
}

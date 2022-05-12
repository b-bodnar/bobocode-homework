package com.bobcode.concurrency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=15&api_key=DEMO_KEY"))
                .GET()
                .build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


    }
}

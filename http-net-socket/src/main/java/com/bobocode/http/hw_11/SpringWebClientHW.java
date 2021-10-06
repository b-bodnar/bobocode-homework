package com.bobocode.http.hw_11;

import org.springframework.web.reactive.function.client.WebClient;

public class SpringWebClientHW {
    private static final String LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";

    public static void main(String[] args) {
        WebClient webClient = WebClient.create();


    }
}

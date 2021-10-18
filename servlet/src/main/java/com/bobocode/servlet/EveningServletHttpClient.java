package com.bobocode.servlet;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EveningServletHttpClient {

    @SneakyThrows
    public static void main(String[] args) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/app/evening?name=Bohdan"))
                .GET()
                .build();
        var response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

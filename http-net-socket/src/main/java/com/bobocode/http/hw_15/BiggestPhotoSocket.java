package com.bobocode.http.hw_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class BiggestPhotoSocket {
    private static final String LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY";

    public static void main(String[] args) {
        try (var socket = new Socket("54.227.149.47", 443)) {
            var writer = new PrintWriter(socket.getOutputStream());
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("GET /mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY HTTP/1.1");
            writer.flush();

            reader.lines().forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

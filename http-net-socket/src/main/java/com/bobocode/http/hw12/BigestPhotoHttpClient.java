package com.bobocode.http.hw12;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class BigestPhotoHttpClient {
    private static final String LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY";

    public static void main(String[] args) throws IOException, InterruptedException {
        var mapper = new ObjectMapper();
        var client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(LINK))
                .build();

        var response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        var body = response.body();

        // print the bigest photos size
        mapper.readValue(body, Photos.class).getPhotos().stream()
                .map(Photo::getImageName)
                .map(photoName -> getPhotoLinkWithSize(client, photoName))
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .ifPresent(e -> System.out.print("photo " + e.getKey() + " size " + e.getValue()));
    }

    private static Map.Entry<String, Integer> getPhotoLinkWithSize(HttpClient client, String photoName) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(photoName))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .build();

            var length = Integer.valueOf(
                    client.send(request, HttpResponse.BodyHandlers.discarding())
                            .headers()
                            .firstValue("content-length")
                            .orElse("0")
            );
            return Map.entry(photoName, length);
        } catch (IOException | InterruptedException e) {
            return Map.entry("", 0);
        }
    }
}

@Getter
class Photos {
    private List<Photo> photos;
}

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Photo {
    @JsonProperty("img_src")
    private String imageName;
}

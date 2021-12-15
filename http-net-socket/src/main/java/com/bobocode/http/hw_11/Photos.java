package com.bobocode.http.hw_11;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
class Photos {
    private List<Photo> listPhotos;
}

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Photo {
    @JsonProperty("img_src")
    private String imageName;
}

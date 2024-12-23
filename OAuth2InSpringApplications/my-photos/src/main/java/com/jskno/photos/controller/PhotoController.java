package com.jskno.photos.controller;

import com.jskno.photos.domain.PhotoRest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @GetMapping
    public List<PhotoRest> getPhotos() {
        PhotoRest photo1 = PhotoRest.builder()
            .id("1")
            .albumId("45")
            .userId("12")
            .title("In Brussels")
            .description("Above the bridge")
            .url("www.my.photo.com")
            .build();

        PhotoRest photo2 = PhotoRest.builder()
            .id("2")
            .albumId("47")
            .userId("13")
            .title("In Budapest")
            .description("Under the bridge")
            .url("www.my.budapest-photo.com")
            .build();

        return List.of(photo1, photo2);
    }

}

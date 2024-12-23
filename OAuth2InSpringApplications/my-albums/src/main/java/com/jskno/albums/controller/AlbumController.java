package com.jskno.albums.controller;

import com.jskno.albums.domain.AlbumRest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @GetMapping
    public List<AlbumRest> getPhotos() {
        AlbumRest album1 = AlbumRest.builder()
            .id("45")
            .userId("312")
            .title("In Brussels")
            .description("Above the bridge")
            .url("www.my.photo.com")
            .build();

        AlbumRest album2 = AlbumRest.builder()
            .id("47")
            .userId("13")
            .title("In Budapest")
            .description("Under the bridge")
            .url("www.my.budapest-photo.com")
            .build();

        return List.of(album1, album2);
    }

}

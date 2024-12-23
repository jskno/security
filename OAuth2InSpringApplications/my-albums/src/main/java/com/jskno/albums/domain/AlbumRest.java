package com.jskno.albums.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumRest {

    private String id;
    private String userId;
    private String title;
    private String description;
    private String url;

}

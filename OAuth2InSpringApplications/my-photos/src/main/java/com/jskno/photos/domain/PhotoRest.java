package com.jskno.photos.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoRest {

    private String id;
    private String albumId;
    private String userId;
    private String title;
    private String description;
    private String url;

}

package com.jskno.photo.app.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumRest {

    private String id;
    private String userId;
    private String title;
    private String description;
    private String url;

}

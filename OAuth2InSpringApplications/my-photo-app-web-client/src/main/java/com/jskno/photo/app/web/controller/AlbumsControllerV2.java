package com.jskno.photo.app.web.controller;

import com.jskno.photo.app.web.domain.AlbumRest;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AlbumsControllerV2 {

    private final OAuth2AuthorizedClientService oath2ClientService;
    private final WebClient webClient;

    @GetMapping("v2/albums")
    public String getAlbums(Model model) {

        List<AlbumRest> albums = webClient.get()
            .uri("http://localhost:8084/albums")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {})
            .block();
        model.addAttribute("albums", albums == null ? Collections.EMPTY_LIST: albums);

        return "albums";
    }

}

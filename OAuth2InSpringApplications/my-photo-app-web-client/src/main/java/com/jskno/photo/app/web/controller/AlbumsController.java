package com.jskno.photo.app.web.controller;

import com.jskno.photo.app.web.domain.AlbumRest;
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

@Controller
@RequiredArgsConstructor
@Slf4j
public class AlbumsController {

    private final OAuth2AuthorizedClientService oath2ClientService;
    private final RestTemplate restTemplate;

    @GetMapping("v1/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal,
        Authentication authentication1) {

        log.info("You can access authentication object of two ways:");
        log.info("1. Injecting in the method: {}", authentication1);

        Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
        log.info("2. From the SecurityContextHolder: {}", authentication2);
        OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) authentication2;

        OAuth2AuthorizedClient oAuth2AuthorizedClient = oath2ClientService.loadAuthorizedClient(
            oAuth2Token.getAuthorizedClientRegistrationId(),
            oAuth2Token.getName());

        String jwtAccessToken = null;
        if(oAuth2AuthorizedClient != null) {
            jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
            log.info("jwtAccessToken: {}", jwtAccessToken);
        }

        log.info("Principal: {}", principal);
        log.info(principal.getIdToken().getTokenValue());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<AlbumRest>> exchange = restTemplate.exchange(
            "http://localhost:8084/albums",
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<>() {});
        model.addAttribute("albums", exchange.getBody());

        return "albums";
    }

}

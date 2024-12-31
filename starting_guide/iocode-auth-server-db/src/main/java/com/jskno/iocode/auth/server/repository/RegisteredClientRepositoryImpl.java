package com.jskno.iocode.auth.server.repository;

import com.jskno.iocode.auth.server.entity.CustomRegisteredClient;
import com.jskno.iocode.auth.server.entity.CustomRegisteredClientRepository;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisteredClientRepositoryImpl implements RegisteredClientRepository {

    private final CustomRegisteredClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        // This function does nothing it is just here to be implemented for contract's sake
    }

    @Override
    public RegisteredClient findById(String id) {
        return mapToClient(clientRepository.findById(id).orElseThrow());
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return mapToClient(clientRepository.findByClientId(clientId).orElseThrow());
    }

    public CustomRegisteredClient save(CustomRegisteredClient client) {
        return clientRepository.save(client);
    }

    public List<CustomRegisteredClient> findAll() {
        return clientRepository.findAll();
    }

    private RegisteredClient mapToClient(CustomRegisteredClient customRegisteredClient) {
        var type = customRegisteredClient.getGrantType().equals("client_credentials") ? AuthorizationGrantType.CLIENT_CREDENTIALS :
            AuthorizationGrantType.AUTHORIZATION_CODE;
        var tokenFormat = customRegisteredClient.getTokenFormat().equals("reference") ? OAuth2TokenFormat.REFERENCE :
            OAuth2TokenFormat.SELF_CONTAINED;
        var clientSettings = customRegisteredClient.isRequireProofKey() ?
            ClientSettings.builder().requireProofKey(true).build() :
            ClientSettings.builder().requireProofKey(false).build();

        return RegisteredClient
            .withId(customRegisteredClient.getId())
            .clientId(customRegisteredClient.getClientId())
            .clientSecret(customRegisteredClient.getClientSecret())
            .redirectUri(customRegisteredClient.getRedirectUri())
            .authorizationGrantType(type)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .tokenSettings(TokenSettings.builder()
                .accessTokenFormat(tokenFormat)
                .accessTokenTimeToLive(Duration.ofHours(12))
                .build())
            .clientSettings(clientSettings)
            .scope("openid")
            .build();
    }
}

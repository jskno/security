package com.jskno.iocode.auth.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients", schema = "auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomRegisteredClient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String clientId;

    private String clientSecret;

    private String grantType;

    @Column(nullable = false)
    private String redirectUri;

    @Column(unique = true)
    private String clientName;

    private boolean requireProofKey;

    private String tokenFormat;

}

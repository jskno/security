package com.jskno.iocode.auth.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Configuration
public class CodeChallengerGenerator implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CodeChallengerGenerator.class);

    @Override
    public void run(String... args) throws Exception {
        byte[] code = new byte[32];
        new SecureRandom().nextBytes(code);
        String verifier = Base64.getUrlEncoder().withoutPadding().encodeToString(code);

        byte[] digestedVerifier = MessageDigest.getInstance("SHA-256").digest(verifier.getBytes());
        String codeChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(digestedVerifier);

        log.info("Verifier: {}", verifier);
        log.info("CodeChallenge: {}", codeChallenge);
    }
}

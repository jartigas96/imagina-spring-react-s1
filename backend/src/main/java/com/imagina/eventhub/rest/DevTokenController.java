package com.imagina.eventhub.rest;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Emisor de tokens para desarrollo local (sustituye al IdP corporativo durante las prácticas).
 * Ejemplo: GET /dev/token?user=c1&role=USER
 */
@RestController
public class DevTokenController {

    private final byte[] secret;

    public DevTokenController(@Value("${eventhub.security.jwt-secret}") String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @GetMapping("/dev/token")
    public Map<String, String> token(@RequestParam String user,
                                     @RequestParam(defaultValue = "USER") String role) throws JOSEException {
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user)
                .issuer("eventhub-dev")
                .claim("roles", Collections.singletonList(role))
                .claim("scope", "orders:read orders:write")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 8L * 3600 * 1000))
                .build();
        SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
        jwt.sign(new MACSigner(secret));
        return Collections.singletonMap("token", jwt.serialize());
    }
}

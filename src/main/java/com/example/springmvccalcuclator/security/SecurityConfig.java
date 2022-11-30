package com.example.springmvccalcuclator.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPublicKey;
import java.util.List;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.AUD;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}")
    private RSAPublicKey key;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {

        JwtDecoder jwtDecoder1 = jwtDecoder();
        boolean equals = jwtDecoder1 == jwtDecoder;
        System.out.println("DECODERS: " + equals);
        return http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/calculator/**").hasAuthority("SCOPE_getOperation")
                        .anyRequest().authenticated())
                .csrf().disable()
                .oauth2ResourceServer(oauth2ResourceServer -> {
                    oauth2ResourceServer.jwt(jwt -> {
                        jwt.decoder(jwtDecoder);
                    });
                }).build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(this.key).build();
        jwtDecoder.setJwtValidator(audienceValidator());
        return jwtDecoder;
    }

    @Bean
    OAuth2TokenValidator<Jwt> audienceValidator() {
        return new JwtClaimValidator<List<String>>(AUD, aud -> aud.contains("messaging"));
    }
}

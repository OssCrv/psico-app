package com.psicotaller.psicoapp.backend.web.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application.jwt")
@Configuration("jwt")
@Data
public class Jwt {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;
}

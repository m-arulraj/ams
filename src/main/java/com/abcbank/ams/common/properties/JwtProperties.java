package com.abcbank.ams.common.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Data
@EqualsAndHashCode
public class JwtProperties {

    private static final Logger log = LoggerFactory.getLogger(JwtProperties.class);

    @NotNull
    private boolean enabled;

    @NotNull
    private String secret;

    @PostConstruct
    public void printProperties() {
        log.info(toString());
    }

}

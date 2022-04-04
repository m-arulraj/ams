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
@ConfigurationProperties(prefix = "security.user-otp")
@Data
@EqualsAndHashCode
public class OtpProperties {

    private static final Logger log = LoggerFactory.getLogger(OtpProperties.class);

    @NotNull
    private Integer code;

    @PostConstruct
    public void printProperties() {
        log.info(toString());
    }

}

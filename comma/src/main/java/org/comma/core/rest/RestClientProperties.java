package org.comma.core.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "comma.rest")
@Validated
public class RestClientProperties {
    @Value("${connection.tomeout}")
    private int connectTimeOut;
    @Value("${read.timeout}")
    private int readTimeOut;
}

package org.comma.config;

import org.comma.core.rest.DefaultRestClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "comma.application")
public class CommaConfiguration {

    private Map<String, String> commaProperties;

    public CommaConfiguration() {
        commaProperties = new HashMap<>();
    }

    @Bean(
            name = {"org.springframework.web.client.RestTemplate"}
    )
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }

    @Bean(
            name = "DefaultRestClient"
    )
    public DefaultRestClient deafultRestApiClient() {
        return new DefaultRestClient();
    }

//    @Bean(
//            name = {"org.springframework.web.async.function.client.WebClient"}
//    )
//    public WebClient defaultWebClient() {
//        return new WebClient.Builder().build();
//    }
}

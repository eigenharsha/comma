package org.comma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "comma")
@Validated
public class ApplicatonConfig {
    private String name;
    private String desc;

    /**
     * configure date format separator using properties
     * comma.date.separator=/
     * '-' used as default date separator.
     */
    @Value("${date.separator:-}")
    private String dateSperator = "-";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

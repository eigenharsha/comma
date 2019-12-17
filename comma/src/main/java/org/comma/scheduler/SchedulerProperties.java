package org.comma.scheduler;

import org.comma.core.ApiClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "comma.scheduler")
@Validated
public class SchedulerProperties implements ApiClientProperties {

    @Nullable
    private String host;

    @Nullable
    private String api;

    @Nullable
    private int port = -1;

    @Nullable
    private String url;

    @Nullable
    private int timeOut = -1;

    public SchedulerProperties() {
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getApi() {
        return api;
    }

    @Override
    public void setApi(String api) {
        this.api = api;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}

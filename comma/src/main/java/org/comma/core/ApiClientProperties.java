package org.comma.core;

public interface ApiClientProperties {
    String getHost();
    String getApi();
    String getUrl();
    int getPort();

    void setHost(String host);
    void setApi(String api);
    void setUrl(String url);
    void setPort(int port);
}

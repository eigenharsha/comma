package org.comma.core.uri.builder;

import javafx.util.Builder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

class InternalUriBuilder implements Builder<URI> {

    private String host;
    private String api;
    private String[] paths;
    private Map<String, String> queryParams;
    private String scheme;
    private int port;

    InternalUriBuilder host(String host) {
        this.host = host;
        return this;
    }

    InternalUriBuilder api(String api) {
        this.api = api;
        return this;
    }

    InternalUriBuilder pathSegment(String... paths) {
        this.paths = paths;
        return this;
    }

    InternalUriBuilder queryPrams(Map<String, String> queryPrams) {
        this.queryParams = queryPrams;
        return this;
    }

    InternalUriBuilder scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    InternalUriBuilder port(int port) {
        this.port = port;
        return this;
    }
    /**
     * Builds and returns the object.
     */
    @Override
    public URI build() {

        String uri;
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("");
        uriComponentsBuilder.host(host);
        uriComponentsBuilder.scheme(scheme);
        uriComponentsBuilder.pathSegment(api);
        if(port > 0) {
            uriComponentsBuilder.port(port);
        }

        for (Map.Entry<String, String> param : queryParams.entrySet()) {
            uriComponentsBuilder.queryParam(param.getKey(), param.getValue());
        }

        // adding path segment
        for(String path : paths) {
            uriComponentsBuilder.pathSegment(path);
        }

        return uriComponentsBuilder.build().toUri();
    }
}

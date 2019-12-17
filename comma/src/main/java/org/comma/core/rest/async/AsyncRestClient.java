package org.comma.core.rest.async;

import org.comma.core.rest.RestClient;
import org.comma.core.rest.request.ApiRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class AsyncRestClient implements RestClient {
    @Override
    public <T> ResponseEntity<T> execute(ApiRequest apiRequest, HttpMethod httpMethod, Class<T> responseType) {
//        WebClient.create()
        return null;
    }

    @Override
    public <T> ResponseEntity<T> get(ApiRequest apiRequest, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> post(ApiRequest apiRequest, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> delete(ApiRequest apiRequest, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> put(ApiRequest apiRequest, Class<T> responseType) {
        return null;
    }
}

package org.comma.core.rest;

import org.comma.core.rest.request.ApiRequest;
import org.comma.exception.model.CommaException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface RestClient {
    <T> ResponseEntity<T> execute(ApiRequest apiRequest, HttpMethod httpMethod, Class<T> responseType) throws CommaException;

    <T> ResponseEntity<T> get(ApiRequest apiRequest, Class<T> responseType) throws CommaException;

    <T> ResponseEntity<T> post(ApiRequest apiRequest, Class<T> responseType) throws CommaException;
    <T> ResponseEntity<T> delete(ApiRequest apiRequest, Class<T> responseType);
    <T> ResponseEntity<T> put(ApiRequest apiRequest, Class<T> responseType);

    interface  Builder {

        // Static, factory methods

        /**
         * Create a new {@code WebClient} with Reactor Netty by default.
         * @see #create(ApiRequest)
         */
        RestClient create();

        RestClient createAsync();

        RestClient create(ApiRequest request);
    }
}

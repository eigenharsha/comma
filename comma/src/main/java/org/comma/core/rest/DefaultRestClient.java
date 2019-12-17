package org.comma.core.rest;

import org.comma.exception.ExceptionHelper;
import org.comma.core.rest.request.ApiRequest;
import org.comma.exception.model.CommaException;
//import org.ebenso.comma.provider.CommaContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

public class DefaultRestClient implements RestClient {

    private RestTemplate restTemplate;

    @Override
    public <T extends Object> ResponseEntity<T> execute(@Valid ApiRequest apiRequest,
                                                        HttpMethod httpMethod,
                                                        Class<T> responseType) throws CommaException {

        ExceptionHelper.throwIfArgumentIsNull(apiRequest);
        ResponseEntity<T> response;

//        restTemplate = CommaContext.getBean(RestTemplate.class);
        ExceptionHelper.throwIfArgumentIsNull(restTemplate);
        String url = apiRequest.getUrl().toString();

        response = restTemplate.exchange(url, httpMethod, apiRequest.getHttpEntity(), responseType);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            return null;
        }
    }

    @Override
    public <T> ResponseEntity<T> get(ApiRequest apiRequest, Class<T> responseType) throws CommaException {
        // explicitly configure GET method
        apiRequest.setHttpMethod(HttpMethod.GET);
        return this.execute(apiRequest, apiRequest.getMethod(), responseType);
    }

    @Override
    public <T> ResponseEntity<T> post(ApiRequest apiRequest, Class<T> responseType) throws CommaException {
        // explicitly configure GET method
        apiRequest.setHttpMethod(HttpMethod.POST);
        return this.execute(apiRequest, apiRequest.getMethod(), responseType);
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

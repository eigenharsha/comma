package org.comma.core.rest.request;

import org.comma.core.rest.ApiURL;
import org.comma.core.uri.builder.UriBuilder;
import javafx.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 */
public class ApiRequest implements HttpRequest, ApiURL {
    private URL url;
    private HttpEntity<Object> httpEntity;
    private HttpHeaders httpHeaders;

    @Override
    public HttpMethod getMethod() {
        return this.httpMethod;
    }
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    private HttpMethod httpMethod;

    public ApiRequest(URL url, HttpEntity<Object> httpEntity) {
        this.url = url;
        this.httpEntity = httpEntity;
        this.httpHeaders = httpEntity.getHeaders();
        this.httpMethod = httpMethod.TRACE;
    }

    public ApiRequest(String protocol, String host, int port, String query) throws MalformedURLException {
        url = new URL(protocol, host, port, query);
    }

    public ApiRequest(String protocol, String host, String query) throws MalformedURLException {
        url = new URL(protocol, host, query);
    }

    public ApiRequest(String url) throws java.lang.Exception {
        URI uri = new URI(url);

        //TODO:: logger + exception

        this.url = new UriBuilder()
                .uri(uri)
                .build();
    }

    public ApiRequest(URL url) {
        this.url = url;
    }

    public HttpEntity<Object> getHttpHeaders() {
        return httpEntity;
    }

    public void setHttpHeaders(HttpEntity<Object> httpEntity) {
        this.httpEntity = httpEntity;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String getMethodValue() {
        return null;
    }

    /**
     * @return URI
     */
    @Override
    public URI getURI() {
        URI uri = null;
        try {
            uri = this.url.toURI();
        } catch (URISyntaxException ex) {
            //TODO : log the exception
        }
        return uri;

    }

    public HttpEntity<Object> getHttpEntity() {
        return httpEntity;
    }

    @Override
    public String getQuery() {
        return url.getQuery();
    }

    @Override
    public String getPath() {
        return url.getPath();
    }

    @Override
    public int getPort() {
        return url.getPort();
    }

    @Override
    public int getDefaultPort() {
        return url.getDefaultPort();
    }

    @Override
    public String getProtocol() {
        return url.getProtocol();
    }

    @Override
    public String getHost() {
        return url.getHost();
    }

    @Override
    public URI toURI() throws URISyntaxException {
        return this.url.toURI();
    }

    @Override
    public URL toURL() {
        return this.url;
    }

    @Override
    public HttpHeaders getHeaders() {
        return httpHeaders;
    }

    /**
     * @param param pair<key, value> adding to httpHeader
     */
    public void appendHeader(Pair<String, String> param) {
        this.httpHeaders.add(param.getKey(), param.getValue());
        // update entity headers
        httpEntity = new HttpEntity<>(httpHeaders);
    }

    /**
     * append the coming httpHeader to the existing http headers.
     * after building the http header it will create the http-entity.
     * @param httpHeaders appending httpHeader to existing httpHeader in httpEntity
     */
    public void appendHeader(HttpHeaders httpHeaders) {
        this.httpHeaders.addAll(httpHeaders);
        // update entity headers
        httpEntity = new HttpEntity<>(httpHeaders);
    }
}

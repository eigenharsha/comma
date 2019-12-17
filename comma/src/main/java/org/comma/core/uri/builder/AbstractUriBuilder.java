package org.comma.core.uri.builder;

import org.comma.exception.ExceptionHelper;
import org.comma.exception.Message;
import org.comma.util.Utils;
import javafx.util.Builder;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractUriBuilder<T extends AbstractUriBuilder<T>> implements Builder<URL> {

    private final String schemeRegex = "^(http|https):\\/\\/";
    private final String SECURE_HTTP_PROTOCOL = "https";
    private final String DEFAULT_HTTP_PROTOCOL = "http";
    private final Pattern hostSchemePattern;
    private int DEFAULT_PORT = -1;

    @NotNull
    private String host;
    private String apiPath;

    @NotNull
    private String scheme;
    private int port = DEFAULT_PORT;
    private String baseUrl;
    private String url;
    private String pathParam;

    private boolean isSchemeConfiguredThroughHost = false;
    private boolean isUriConfigured = false;

    private Map<String, String> queryParams;
    private String id;
    private int order = 0;
    private URI uri;

    protected AbstractUriBuilder() throws Exception {
        queryParams = new HashMap<String, String>();
        hostSchemePattern = Pattern.compile(schemeRegex, Pattern.MULTILINE);
        host = "";
        port = -1;
        apiPath = null;
        setScheme(DEFAULT_HTTP_PROTOCOL);
    }

    protected AbstractUriBuilder(String host) throws Exception {
        this();
        this.setHost(host);
    }

    protected abstract T getThis();

    public T host(@NotNull String host) throws Exception {
        setHost(host);
        return getThis();
    }

    public T secure() throws Exception {
        this.setScheme(SECURE_HTTP_PROTOCOL); // default using 'default protocol'
        return getThis();
    }

    public T initializeDefaultProtocol() throws Exception {
        this.setScheme(DEFAULT_HTTP_PROTOCOL); // default using 'default protocol'
        return getThis();
    }

    public T resourcePath(String resourcePath) {
        this.apiPath = resourcePath;

        // validate the prefix of resource path.
        char pathSeperator = resourcePath.trim().charAt(0);
        apiPath = (pathSeperator == '/') ? resourcePath.substring(1, resourcePath.length()) : resourcePath;
        return getThis();
    }

    public T scheme(@NotNull String scheme) throws Exception {
        this.setScheme(scheme);
        return getThis();
    }

    public T port(int port) {
        this.port = port;
        return getThis();
    }

    public URL build() {
        if (StringUtils.isEmpty(host)) {
            return null;
            // TODO:: LOGExceptionHelper.propagate(Message.ARGUMENTS_ARE_EMPTY_OR_NULL, host);
        }
        try {
            scheme = StringUtils.isEmpty(scheme)
                    ? this.initializeDefaultProtocol().getScheme()
                    : scheme;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // url and api path as optional params,
        // validate these params and if not present then fill the default value.
        // it will neglect the null pointer exception.
        apiPath = (Utils.isNull(apiPath)) ? "" : apiPath;
        url = (Utils.isNull(url)) ? "" : url;

        try {
            // URI constructor signature
            // URI(String scheme, String userInfo, String host, int port,
            // String path, String pathParam, String fragment)
            URI localUri;
            localUri = new InternalUriBuilder()
                    .host(host)
                    .scheme(scheme)
                    .api(apiPath)
                    .queryPrams(queryParams)
                    .pathSegment(pathParam)
                    .port(port)
                    .build();

            // after successful build the uri, need to construct all the
            // required information that provided by through getter.
            baseUrl = localUri.getHost();
            host = localUri.getHost();
            apiPath = localUri.getPath();
            port = localUri.getPort();
            pathParam = localUri.getQuery();
            url = localUri.getPath();
            return localUri.toURL();

        } catch (MalformedURLException e) {
//           TODO:: LOG ExceptionHelper.propagate(e);
        }
        return null;
    }

    public T addPathParam(@NotNull String param) {
        this.pathParam = param;
        return getThis();
    }

    public T appendPathParam(@NotNull String param) {
        Utils.requireNonNullandNotEmpty(param, "path parameters should be a valid string");
        this.pathParam += ("/" + param);
        return getThis();
    }

    public T setQueryParam(@NotNull String name, @Nullable String value) {
        queryParams.clear();
        queryParams.put(name, value);
        return getThis();
    }

    public T addPathParam(@NotNull String name, @Nullable String value) {
        queryParams.put(name, value);
        return getThis();
    }

    public String getHost() {
        return host;
    }

    protected void setHost(String host) throws Exception {
        if (StringUtils.isEmpty(host)) {
            ExceptionHelper
                    .propagate(Message.ARGUMENTS_ARE_EMPTY_OR_NULL, host);
        } else {
            final Matcher matcher = hostSchemePattern.matcher(host);
            if (matcher.groupCount() > 0) { // http or https found in host url
                if (matcher.find()) {
                    // remove :// from scheme
                    String tempScheme = matcher.group(0);
                    tempScheme = tempScheme.replaceAll(":\\/\\/", "");
                    setScheme(tempScheme);

                    // if host has scheme then
                    // we are not going to overwrite the scheme in future.
                    isSchemeConfiguredThroughHost = true;
                } else {
                    isSchemeConfiguredThroughHost = false;
                    setScheme(DEFAULT_HTTP_PROTOCOL); //default scheme
                }

                // we need to remove it form host
                // and update our scheme (overwrite scheme)
                host = host.replaceAll(schemeRegex, "");

                // truncate / if host has '/' (host end character)
                int hostEndCharIndex = host.lastIndexOf('/');
                host = (hostEndCharIndex == host.length() - 1)
                        ? host.substring(0, host.length() - 1)
                        : host;
            }
        }
        this.host = host;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getScheme() {
        return scheme;
    }

    private void setScheme(String scheme) throws Exception {
        if (isSchemeConfiguredThroughHost)
            return;
        if (StringUtils.isEmpty(scheme)) {
            throw ExceptionHelper.propagate(Message.PROTOCOL_IS_EMPTY_OR_NULL);
        } else if (scheme.equalsIgnoreCase(DEFAULT_HTTP_PROTOCOL)) {
            this.scheme = DEFAULT_HTTP_PROTOCOL;
        } else if (scheme.equalsIgnoreCase(SECURE_HTTP_PROTOCOL)) {
            this.scheme = SECURE_HTTP_PROTOCOL;
        } else {
            throw ExceptionHelper.propagate(new IllegalArgumentException("unexpected scheme: " + scheme));
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public T PathParams(Objects[] params) {
        for (Object obj : params) {
            RestTemplate template = new RestTemplate();
            template.getForObject("", String.class, params);
        }
        return getThis();
    }

    public T addQueryParams(Map<String, String> params) {
        this.queryParams = params;
        return getThis();
    }

    public T id(String id) {
        this.id = id;
        return getThis();
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public T uri(URI uri) {
        this.processUri(uri);
        return getThis();
    }

    private void processUri(URI uri) {
        this.isUriConfigured = true;
        baseUrl = uri.getHost();
        host = uri.getHost();
        apiPath = uri.getPath();
        port = uri.getPort();
        pathParam = uri.getQuery();
        this.uri = uri;
    }

    public T order(int order) {
        this.order = order;
        return getThis();
    }

    public T queryParam(String query) {
        this.addPathParam(query);
        return getThis();
    }

    public String getPathParam() {
        return pathParam;
    }

    public void query(String query) {
        this.pathParam = query;
    }
}


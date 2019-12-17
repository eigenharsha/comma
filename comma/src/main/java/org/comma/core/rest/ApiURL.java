package org.comma.core.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public interface ApiURL {
    public String getQuery();
    public String getPath();
    public int getPort();
    public int getDefaultPort();
    public String getProtocol();
    public String getHost();
    public URI toURI() throws URISyntaxException;
    public URL toURL();
}

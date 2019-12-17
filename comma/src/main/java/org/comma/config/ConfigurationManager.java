package org.comma.config;

import java.net.NoRouteToHostException;

/**
 *
 */
public interface ConfigurationManager {
    String getUrl();

    String getHost() throws NoRouteToHostException;

    String getBaseUrl() throws NoRouteToHostException;
}

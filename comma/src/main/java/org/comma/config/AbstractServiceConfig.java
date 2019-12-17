package org.comma.config;//package org.ebenso.acpcommon.org.ebenso.acp.common.config;
//
//import org.ebenso.acpcommon.builder.AbstractUriBuilder;
//import org.ebenso.acpcommon.Message;
//import org.ebenso.acpcommon.ExceptionHelper;
//import org.ebenso.acpcommon.org.ebenso.acp.common.exception.model.Exceptions;
//import org.springframework.org.ebenso.acp.common.util.StringUtils;
//
//import java.net.MalformedURLException;
//import java.net.NoRouteToHostException;
//
//public abstract class AbstractServiceConfig implements ConfigurationManager {
//
//    protected String host;
//    protected String path;
//    protected String url;
//    protected int port = -1;
//    protected boolean isSecure;
//
//    protected String protocol = "http";
//
//    public AbstractServiceConfig(String host, int port, String path, boolean isSecure) {
//        this.host = host;
//        this.path = path;
//        this.isSecure = isSecure;
//        this.port = port;
//        this.buildUrl();
//    }
//
//    public AbstractServiceConfig(String host, String path, String secure) {
//        this.host = host;
//        this.path = path;
//        if (!StringUtils.isEmpty(secure))
//            this.isSecure = Boolean.parseBoolean(secure);
//        else
//            this.isSecure = false;
//
//        this.buildUrl();
//    }
//
//    public String getHost() throws NoRouteToHostException {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public void buildUrl() throws Exceptions {
//        AbstractUriBuilder builder = new AbstractUriBuilder();
//        try {
//            builder.withHost(host).
//                    withPort(port).
//                    withResourcePath(path).
//                    withScheme(protocol).build();
//        } catch (MalformedURLException e) {
//            throw ExceptionHelper.multiple(Message.INVALID_ARGUMENT_EXCEPTION,
//                    e, host, port, path, protocol);
//        }
//    }
//
//    public boolean isSecure() {
//        return isSecure;
//    }
//
//    public void setSecure(boolean secure) {
//        isSecure = secure;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public String buildHost() throws MalformedURLException {
//        return new AbstractUriBuilder()
//                .withHost(host).build().getBaseUrl();
//    }
//}

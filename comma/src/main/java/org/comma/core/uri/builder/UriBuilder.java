package org.comma.core.uri.builder;

public class UriBuilder extends AbstractUriBuilder<UriBuilder> {

    public UriBuilder() throws java.lang.Exception {
        super("");
    }

    protected UriBuilder(String host) throws Exception {
        super(host);
    }

    @Override
    protected UriBuilder getThis() {
        return this;
    }
}

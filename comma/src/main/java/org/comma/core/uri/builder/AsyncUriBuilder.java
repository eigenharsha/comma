package org.comma.core.uri.builder;

public class AsyncUriBuilder extends AbstractUriBuilder<AsyncUriBuilder> {

    protected AsyncUriBuilder() throws java.lang.Exception {
        super("");
    }

    protected AsyncUriBuilder(String host) throws Exception {
        super(host);
    }

    @Override
    protected AsyncUriBuilder getThis() {
        return this;
    }
}

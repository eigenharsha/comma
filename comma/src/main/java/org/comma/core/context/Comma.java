package org.comma.core.context;

import org.comma.exception.rest.DefaultExceptionBuilder;
import org.comma.exception.rest.ExceptionBuilder;

public final class Comma {
    private static ExceptionBuilder exceptionBuilder;

    public void Comma() {
        exceptionBuilder = new DefaultExceptionBuilder();
    }

    public static void exceptionBuilder(ExceptionBuilder builder) {
        exceptionBuilder = builder;
    }

    public static ExceptionBuilder getExceptionBuilder() {
        return exceptionBuilder;
    }

}

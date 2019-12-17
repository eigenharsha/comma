package org.comma.exception.rest;

import org.comma.exception.provider.AbstractBeanProvider;
import org.springframework.stereotype.Component;

@Component
public class ExceptionBuilderFactory {

    private ExceptionBuilder exceptionBuilder;

    public ExceptionBuilder getExceptionBuilder() {
        try {
            exceptionBuilder = AbstractBeanProvider.getBean(ExceptionBuilder.class);
        } catch (Exception ex) {
//            exceptionBuilder = new DefaultExceptionBuilder();
            exceptionBuilder = null;
        }
        return exceptionBuilder;
    }
}

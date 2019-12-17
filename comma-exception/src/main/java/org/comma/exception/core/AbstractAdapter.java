package org.comma.exception.core;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAdapter implements GettingObject {

    private ObjectPostProcessor<Object> objectPostProcessor;

    @Autowired
    public void setObjectPostProcessor(ObjectPostProcessor<Object> objectPostProcessor) {
        this.objectPostProcessor = objectPostProcessor;
    }

    protected void configure() {

    }
}

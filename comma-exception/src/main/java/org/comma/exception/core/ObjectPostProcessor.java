package org.comma.exception.core;

public interface ObjectPostProcessor<T> {

    /**
     * Initialize the object possibly returning a modified instance that should be used
     * instead.
     *
     * @param object the object to initialize
     * @return the initialized version of the object
     */
    <O extends T> O postProcess(O object);
}

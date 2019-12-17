package org.comma.exception.core.builder;

/**
 * Interface representing a builder. Builders are objects that are used to
 * construct other objects.
 */
@FunctionalInterface
public interface Builder<T> {
    /**
     * Builds and returns the object.
     */
    public T build();
}

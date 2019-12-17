package org.comma.parser;

/**
 * Interface representing a parser. Parser are objects that are used to
 * construct other objects.
 */
public interface Parser<T> {

    public T parse();
}

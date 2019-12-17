package org.comma.core.retry;

public @interface RetryOnFailure {
    int DEFAULT_ATTEMPTS = 3;

    int attempts() default  3;

    boolean randomize();
}

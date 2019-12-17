package org.comma.core.retry;

/**
 * @author Kumar Harsha (kumar.harsha@oyorooms.com)
 */

import org.springframework.scheduling.annotation.Async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Async
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface AsyncRetryOnFailure {

    /**
     * the number of attempts to repeat the function execution
     * if function throw any exception.
     * @return int
     */
    int attempt() default 3;

    String callback() default "";

    boolean randomize();

    long delay();

    TimeUnit unit();

    Class<? extends Throwable>[] types();
}

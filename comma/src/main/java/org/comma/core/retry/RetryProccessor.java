//package org.ebenso.comma.core.retry;
//
//import com.jcabi.aspects.Tv;
//import com.jcabi.aspects.aj.ImprovedJoinPoint;
//import com.jcabi.aspects.aj.Repeater;
//import com.jcabi.log.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.reflect.MethodSignature;
//
//import java.lang.reflect.Method;
//
//public class RetryProccessor {
//
//    @Around("execution(* * (..)) && @annotation(AsyncRetryOnFailure)")
//    @SuppressWarnings("PMD.AvoidCatchingThrowable")
//    public Object wrap(final ProceedingJoinPoint point) throws Throwable {
//        final Method method = MethodSignature.class
//                .cast(point.getSignature())
//                .getMethod();
//        final AsyncRetryOnFailure rof = method.getAnnotation(AsyncRetryOnFailure.class);
//        int attempt = 0;
//        final long begin = System.nanoTime();
//        final Class<? extends Throwable>[] types = rof.types();
//        final ImprovedJoinPoint joinpoint = new ImprovedJoinPoint(point);
//        while (true) {
//            final long start = System.nanoTime();
//            try {
//                return point.proceed();
//            } catch (final InterruptedException ex) {
//                Thread.currentThread().interrupt();
//                throw ex;
//                // @checkstyle IllegalCatch (1 line)
//            } catch (final Throwable ex) {
//                if (Repeater.matches(ex.getClass(), rof.ignore())) {
//                    throw ex;
//                }
//                if (!Repeater.matches(ex.getClass(), types)) {
//                    throw ex;
//                }
//                ++attempt;
//                if (rof.verbose()) {
//                    Logger.warn(
//                            joinpoint.targetize(),
//                            // @checkstyle LineLength (1 line)
//                            "#%s(): attempt #%d of %d failed in %[nano]s (%[nano]s waiting already) with %[exception]s",
//                            method.getName(),
//                            attempt, rof.attempts(), System.nanoTime() - start,
//                            System.nanoTime() - begin, ex
//                    );
//                } else {
//                    Logger.warn(
//                            joinpoint.targetize(),
//                            // @checkstyle LineLength (1 line)
//                            "#%s(): attempt #%d/%d failed with %[type]s in %[nano]s (%[nano]s in total): %s",
//                            method.getName(),
//                            attempt, rof.attempts(), ex, System.nanoTime() - start,
//                            System.nanoTime() - begin,
//                            Repeater.message(ex)
//                    );
//                }
//                if (attempt >= rof.attempts()) {
//                    throw ex;
//                }
//                if (rof.delay() > 0L) {
//                    this.delay(rof, attempt);
//                }
//            }
//        }
//    }
//
//    /**
//     * Waits certain time before returning.
//     * @param rof RetryOnFailure parameters.
//     * @param attempt Attempt number.
//     * @throws InterruptedException If wait has been interrupted.
//     */
//    private void delay(final AsyncRetryOnFailure rof, final int attempt) throws
//            InterruptedException {
//        final long delay;
//        if (rof.randomize()) {
//            delay = Repeater.RAND.nextInt(2 << attempt) * rof.delay();
//        } else {
//            delay = rof.delay() * attempt;
//        }
//        rof.unit().sleep(delay);
//    }
//
//    /**
//     * Get a message out of a potentially chained exception (recursively
//     * calls itself in order to reproduce a chain of messages).
//     * @param exp The exception
//     * @return The message
//     */
//    private static String message(final Throwable exp) {
//        final StringBuilder text = new StringBuilder();
//        text.append(exp.getMessage());
//        if (exp.getCause() != null) {
//            text.append("; ").append(Repeater.message(exp.getCause()));
//        }
//        String msg = text.toString();
//        if (msg.length() > Tv.HUNDRED) {
//            msg = String.format("%s...", msg.substring(0, Tv.HUNDRED));
//        }
//        return msg;
//    }
//
//    /**
//     * Checks if the exception thrown matches the list.
//     * @param thrown The thrown exception class
//     * @param types The exceptions to match
//     * @return TRUE if matches
//     */
//    private static boolean matches(
//            final Class<? extends Throwable> thrown,
//            final Class<? extends Throwable>... types
//    ) {
//        boolean matches = false;
//        for (final Class<? extends Throwable> type : types) {
//            if (type.isAssignableFrom(thrown)) {
//                matches = true;
//                break;
//            }
//        }
//        return matches;
//    }
//}

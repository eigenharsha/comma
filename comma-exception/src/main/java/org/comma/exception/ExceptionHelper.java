package org.comma.exception;

import org.comma.exception.model.CommaException;
import org.comma.exception.util.Utils;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Global Core CommaException handling and utils to operate on.
 * reference: https://github.com/reactor/reactive-streams-commons
 * @author kumar harsha (Kumar.harsha@oyorooms.com)
 * @version $Id$
 * @since 0.7.22
 * @see <a href="http://github.com/oyoroomd">http:github.com</a>
 */
public abstract class ExceptionHelper {

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    public static final Throwable TERMINATED = new Throwable("Operator has been terminated");
    static final RejectedExecutionException REJECTED_EXECUTION = new RejectedExecutionException("Scheduler unavailable");

    private static CommaException commaException;

    public ExceptionHelper() {
    }

    public static CommaException multiple(Throwable... throwables) {
        CompositeCommaException multiple = new CompositeCommaException();
        //noinspection ConstantConditions
        if (throwables != null) {
            for (Throwable t : throwables) {
                //this is ok, multiple is always a new non-singleton instance
                multiple.addSuppressed(t);
            }
        }
        return multiple;
    }

    public static CommaException multiple(Iterable<Throwable> throwables) {
        CommaException multiple = new CommaException("Multiple exceptions");
        //noinspection ConstantConditions
        if (throwables != null) {
            for (Throwable t : throwables) {
                //this is ok, multiple is always a new non-singleton instance
                multiple.addSuppressed(t);
            }
        }
        return multiple;
    }

    public static BubblingCommaException bubble(Throwable t) throws BubblingCommaException {
        throwIfFatal(t);
        return new BubblingCommaException(t);
    }

    public static IllegalStateException duplicateOnSubscribeException() {
        return new IllegalStateException(
                "Spec. Rule 2.12 - Subscriber.onSubscribe MUST NOT be called more than once (based on object equality)");
    }


    public static UnsupportedOperationException errorCallbackNotImplemented(Throwable cause) {
        Objects.requireNonNull(cause, "cause");
        return new ErrorCallbackNotImplemented(cause);
    }

    public static CancelCommaException failWithCancel() {
        return new CancelCommaException();
    }


    public static IllegalStateException failWithOverflow() {
        return new OverflowException("The receiver is overrun by more signals than expected (bounded queue...)");
    }


    public static IllegalStateException failWithOverflow(String message) {
        return new OverflowException(message);
    }


    public static RejectedExecutionException failWithRejected() {
        return REJECTED_EXECUTION;
    }

    public static RejectedExecutionException failWithRejected(Throwable cause) {
        if (cause instanceof ReactorRejectedExecutionException) {
            return (RejectedExecutionException) cause;
        }
        return new ReactorRejectedExecutionException("Scheduler unavailable", cause);
    }


    public static boolean isOverflow(@Nullable Throwable t) {
        return t instanceof OverflowException;
    }

    public static boolean isBubbling(@Nullable Throwable t) {
        return t instanceof BubblingCommaException;
    }


    public static boolean isCancel(@Nullable Throwable t) {
        return t instanceof CancelCommaException;
    }

    public static boolean isErrorCallbackNotImplemented(@Nullable Throwable t) {
        return t != null && t.getClass().equals(ErrorCallbackNotImplemented.class);
    }


    public static boolean isMultiple(@Nullable Throwable t) {
        return t instanceof CompositeCommaException;
    }


    public static IllegalArgumentException nullOrNegativeRequestException(long elements) {
        throw new IllegalArgumentException(
                "Spec. Rule 3.9 - Cannot request a non strictly positive number: " + elements);
    }


    public static RuntimeException propagate(Throwable t) throws CommonCommaException {
        throwIfFatal(t);
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        }
        throw new CommonCommaException(t);
    }

    public static RuntimeException propagate(CommaException commaException) throws CommaException {
        throwIfFatal(commaException);
        throw commaException;
    }

    @Nullable
    public static <T> Throwable terminate(AtomicReferenceFieldUpdater<T, Throwable> field,
                                          T instance) throws Throwable {
        Throwable current = field.get(instance);
        if (current != TERMINATED) {
            current = field.getAndSet(instance, TERMINATED);
        }
        throw current;
    }


    public static void throwIfFatal(@Nullable Throwable t) throws BubblingCommaException {
        if (t instanceof BubblingCommaException) {
            throw (BubblingCommaException) t;
        }
        if (t instanceof ErrorCallbackNotImplemented) {
            throw (ErrorCallbackNotImplemented) t;
        }
        throwIfJvmFatal(t);
    }

    public static void throwIfJvmFatal(@Nullable Throwable t) {
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof LinkageError) {
            throw (LinkageError) t;
        }
    }


    public static Throwable unwrap(Throwable t) {
        Throwable _t = t;
        while (_t instanceof CommonCommaException) {
            _t = _t.getCause();
        }
        return _t == null ? t : _t;
    }

    public static CommaException unwrapMultiple(@Nullable Throwable potentialMultiple) {
        if (potentialMultiple == null) {
            return null;
        }

        if (isMultiple(potentialMultiple)) {
            return new CommaException(potentialMultiple);
        }

        return new CommaException(potentialMultiple);
    }

    public static CommaException getCommaException() {
        return commaException;
    }

    public static void setCommaException(CommaException commaException) {
        ExceptionHelper.commaException = commaException;
    }

    public static CommaException propagate(Message message) throws CommaException {
        throw new CommaException(message);
    }

    public static CommaException propagate(Message message, Object... args) throws CommaException {
        return propagate(message);
    }

    public static CommaException multiple(Message message,
                                          RuntimeException e,
                                          Object... args) throws CommaException {

        CommaException ex = propagate(message, args);
        return multiple(ex, e);
    }

    public static CommaException multiple(Message message,
                                          Throwable throwable,
                                          Object... args) throws CommaException {

        CommaException ex = propagate(message, args);
        return multiple(ex, throwable);
    }

    public static CommaException multiple(Message message,
                                          IOException e,
                                          Object... args) throws CommaException {

        CommaException ex = propagate(message, args);
        return multiple(ex, e);
    }

    public static void throwIfArgumentIsNull(Object object) throws CommaException {
        if(Utils.isNull(object)) {
            propagate(Message.ARGUMENTS_ARE_EMPTY_OR_NULL);
        }
    }

    public static void propagate(String message, Throwable exception) throws CommaException {
        throw new CommaException(message, exception);
    }

    static class CompositeCommaException extends CommonCommaException {

        private static final long serialVersionUID = 8070744939537687606L;

        CompositeCommaException() {
            super("Multiple exceptions");
        }
    }

    static class BubblingCommaException extends CommonCommaException {

        private static final long serialVersionUID = 2491425277432776142L;

        BubblingCommaException(String message) {
            super(message);
        }

        BubblingCommaException(Throwable cause) {
            super(cause);
        }
    }

    static final class CancelCommaException extends BubblingCommaException {

        private static final long serialVersionUID = 2491425227432776144L;

        CancelCommaException() {
            super("The subscriber has denied dispatching");
        }

        CancelCommaException(String message) {
            super(message);
        }

    }

    /**
     * An org.ebenso.acp.common.commaException that is propagated downward through {@link org.reactivestreams.Subscriber#onError(Throwable)}
     */
    public static class CommonCommaException extends CommaException {

        private static final long serialVersionUID = 2491425227432776143L;

        CommonCommaException(Throwable cause) {
            super(cause);
        }

        CommonCommaException(String message) {
            super(message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return getCause() != null ? getCause().fillInStackTrace() :
                    super.fillInStackTrace();
        }
    }

    static final class ErrorCallbackNotImplemented extends UnsupportedOperationException {

        private static final long serialVersionUID = 2491425227432776143L;

        ErrorCallbackNotImplemented(Throwable cause) {
            super(cause);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    static final class OverflowException extends IllegalStateException {

        OverflowException(String s) {
            super(s);
        }
    }

    static final class ReactorRejectedExecutionException extends RejectedExecutionException {

        ReactorRejectedExecutionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}


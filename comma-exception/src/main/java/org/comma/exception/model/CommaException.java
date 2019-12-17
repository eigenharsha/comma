package org.comma.exception.model;

import org.comma.exception.core.message.Messages;
import org.comma.exception.ErrorCode;
import org.comma.exception.Message;

import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommaException extends java.lang.Exception {

    /**
     * The default name of the exception attribute: "exception".
     */
    public static final String DEFAULT_EXCEPTION_ATTRIBUTE = "exception";

    private int code;
    private String type;
    private List<CommaException> errors;
    private String message;
    private Map<String, String> params = new HashMap<>();

    public CommaException(Throwable throwable) {
        super(throwable.getMessage());
        code = -1;
        type = DEFAULT_EXCEPTION_ATTRIBUTE;
        errors = new ArrayList<>();
    }

    /**
     * Constructs a new exception with {@code null} as its given ErrorCode.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CommaException(ErrorCode message) {
        super(Messages.getMessage(message.getCode()));
        code = -1;
        type = DEFAULT_EXCEPTION_ATTRIBUTE;
        errors = new ArrayList<>();
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CommaException(String message) {
        super(message);
        code = -1;
        type = DEFAULT_EXCEPTION_ATTRIBUTE;
        errors = new ArrayList<>();
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CommaException(String message, String reason) {
        super(reason);
        this.message = message;
        code = -1;
        type = DEFAULT_EXCEPTION_ATTRIBUTE;
        errors = new ArrayList<>();
    }
//    /**
//     * Constructs a new exception with the specified detail message.  The
//     * cause is not initialized, and may subsequently be initialized by
//     * a call to {@link #initCause}.
//     *
//     * @param message the ErrorCode. via the error code  get the message from \
//     *                the configured resource. The detail message is saved for
//     *                later retrieval by the {@link #getMessage()} method.
//     */
//    public CommaException(ErrorCode message, int code, String type, List<java.lang.CommaException> errors) {
//        super(Messages.getMessage(message.getCode()));
//        this.code = code;
//        this.type = type;
//
//        for(java.lang.CommaException error : errors) {
//            this.errors.add(new CommaException(error));
//        }
//    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the ErrorCode. via the error code  get the message from \
     *                the configured resource. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CommaException(ErrorCode message, int code, String type, List<CommaException> errors) {
        super(Messages.getMessage(message.getCode()));
        this.code = code;
        this.type = type;
        this.errors = errors;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CommaException(String message, int code, String type, List<CommaException> errors) {
        super(message);
        this.code = code;
        this.type = type;
        this.errors = errors;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public CommaException(String message, Throwable cause, int code, String type, List<CommaException> errors) {
        super(message, cause);
        this.code = code;
        this.type = type;
        this.errors = errors;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public CommaException(Throwable cause, int code, String type, List<CommaException> errors) {
        super(cause);
        this.code = code;
        this.type = type;
        this.errors = errors;
    }

    /**
     * Constructs a new exception with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack
     * trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     * @since 1.7
     */
    public CommaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String type, List<CommaException> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.type = type;
        this.errors = errors;
    }

    public CommaException(String message, Throwable cause) {
        super(message, cause);
        code = -1;
        type = DEFAULT_EXCEPTION_ATTRIBUTE;
        errors = new ArrayList<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addCause(java.lang.Exception exception) {
        this.addCause(exception);
    }

    public void addCause(String message) {
        CommaException commaException = new CommaException(message);
        this.errors.add(commaException);
    }

    public void addCause(Message message) {
        CommaException commaException = new CommaException(message);
        this.errors.add(commaException);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {

        String s = getClass().getName();
        String message = getLocalizedMessage();

        StringBuilder builder = new StringBuilder();
        builder.append("message: " + ((message != null) ? (s + ": " + message) : s));
        if (code > 0) {
            builder.append("\n\t").append("code: " + code);
        }
        builder.append("\n\t").append("type: " + type);

        if (errors.size() > 0) {
            List<String> errors = this.errors
                    .stream()
                    .map(x -> {
                        String msg = x.getLocalizedMessage();
                        return "\n\t\t" + msg.trim();
                    })
                    .collect(Collectors.toList());
            builder.append("\n\t").append("cause: " + errors);
        }

        return builder.toString();
    }

    public void addParams(String paramKey, String paramValue) {
        try {
            this.params.put(paramKey, paramValue);
        } catch (java.lang.Exception ex) {

        }
    }

    public List<CommaException> getError() {
        return errors;
    }

    protected void setError(List<CommaException> error) {
        this.errors = error;
    }
}

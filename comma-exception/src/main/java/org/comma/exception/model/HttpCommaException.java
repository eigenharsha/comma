package org.comma.exception.model;

import org.comma.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.List;

public class HttpCommaException extends CommaException {
    private HttpStatus status;

    public HttpCommaException(CommaException commaException, HttpStatus status) {
        super(commaException.getMessage());
        this.status = status;
        this.setCode(commaException.getCode());
        this.setType(commaException.getType());
        this.setError(commaException.getError());
    }

    /**
     * Constructs a new exception with {@code null} as its given ErrorCode.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message error code
     * @param status  http status
     */
    public HttpCommaException(ErrorCode message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message
     */
    public HttpCommaException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the ErrorCode. via the error code  get the message from \
     *                the configured resource. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param code
     * @param type
     * @param errors
     */
    public HttpCommaException(ErrorCode message, int code, String type, List<CommaException> errors, HttpStatus status) {
        super(message, code, type, errors);
        this.status = status;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param code
     * @param type
     * @param errors
     */
    public HttpCommaException(String message, int code, String type, List<CommaException> errors, HttpStatus status) {
        super(message, code, type, errors);
        this.status = status;
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
     * @param code
     * @param type
     * @param errors
     * @since 1.4
     */
    public HttpCommaException(String message, Throwable cause, int code, String type, List<CommaException> errors, HttpStatus status) {
        super(message, cause, code, type, errors);
        this.status = status;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * @param cause  the cause (which is saved for later retrieval by the
     *               {@link #getCause()} method).  (A <tt>null</tt> value is
     *               permitted, and indicates that the cause is nonexistent or
     *               unknown.)
     * @param code
     * @param type
     * @param errors
     * @since 1.4
     */
    public HttpCommaException(Throwable cause, int code, String type, List<CommaException> errors, HttpStatus status) {
        super(cause, code, type, errors);
        this.status = status;
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
     * @param code
     * @param type
     * @param errors
     * @since 1.7
     */
    public HttpCommaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String type, List<CommaException> errors, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace, code, type, errors);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}

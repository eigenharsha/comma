package org.comma.exception.model;//package org.ebenso.comma.exception.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
//import com.google.common.base.MoreObjects;
//import org.ebenso.comma.core.enums.ExceptionType;
//import org.ebenso.comma.core.message.LowerCasePropertyNameResolver;
//import org.ebenso.comma.core.message.Messages;
//import Message;
//import ModelViewException;
//import org.ebenso.comma.util.Utils;
//import org.springframework.http.HttpStatus;
//
//@JsonTypeIdResolver(LowerCasePropertyNameResolver.class)
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//        "success",
//        "status",
//        "message",
//        "error"
//})
//@JsonIgnoreProperties({"stackTrace", "backTrace"})
//public final class RestException extends ModelViewException {
//
//    @JsonProperty("success")
//    protected boolean success;
//
//    @JsonProperty("status")
//    protected HttpStatus status;
//
//    @JsonProperty("message")
//    protected String message;
//
//    @JsonProperty("error")
//    protected Error error;
//
//
//    /**
//     * No args constructor for use in serialization
//     */
//    public RestException() {
//        // default
//        success = false;
//        status = HttpStatus.BAD_REQUEST;
//        error = new Error()
//                .withCode(HttpStatus.BAD_REQUEST)
//                .withMessage(super.getMessage())
//                .withStatus("default-type")
//                .withType(ExceptionType.RuntimeException);
//
//        // if stacktrace enable then add stack trace also
//    }
//
//    public RestException(RuntimeException exception) {
//        super(exception);
//        this.success = false;
//        this.status = HttpStatus.BAD_REQUEST;
//        this.addError(exception.getMessage());
//    }
//
//    /**
//     * @param error
//     * @param success
//     */
//    public RestException(boolean success, Error error) {
//        super();
//        this.success = success;
//        this.error = error;
//    }
//
//    public RestException(String message, java.lang.CommaException ex) {
//        super(message);
//        this.success = false;
//        this.status = HttpStatus.EXPECTATION_FAILED;
//        this.error = (Error) ex;
//    }
//
//    public RestException(Throwable cause) {
//        super(cause);
//        onCreate(false, new Error()
//                .withMessage(cause.getMessage()));
//    }
//
//    public RestException(String message) {
//        super(message);
//        onCreate(false, new Error()
//                .withMessage(message));
//    }
//
//    public RestException(Message message) {
//        super(Messages.getMessage(message.getCode()));
//        this.addError(message);
//    }
//
//    public RestException(String message, Throwable exception) {
//        super(exception);
//        this.success = false;
//        this.error = new Error(message, exception);
//    }
//
//    public void onCreate(boolean success, Error error) {
//        this.setSuccess(success);
//        this.setError(error);
//    }
//
//    @JsonProperty("success")
//    public boolean isSuccess() {
//        return success;
//    }
//
//    @JsonProperty("success")
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    @Override
//    protected void setMessage(String message) {
//
//    }
//
//    @Override
//    protected void addError(CommaException exception) {
//
//    }
//
//    @Override
//    protected void addError(java.lang.CommaException exception) {
//
//    }
//
//    public CommaException withSuccess(boolean success) {
//        this.success = success;
//        return this;
//    }
//
//    @JsonProperty("error")
//    public Error getError() {
//        return error;
//    }
//
//    @JsonProperty("error")
//    public void setError(Error error) {
//        this.error = error;
//    }
//
//    public CommaException withError(Error error) {
//        this.error = error;
//        return this;
//    }
//
//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this)
//                .add("success", success)
//                .add("error", error)
//                .add("status", status)
//                .toString();
//    }
//
//    public HttpStatus getStatus() {
//        return status;
//    }
//
//    public HttpStatus setStatus(HttpStatus status) {
//        return this.status = status;
//    }
//
//    public CommaException withStatus(HttpStatus status) {
//        this.status = status;
//        return this;
//    }
//
//    public CommaException addError(Message message) {
//        if(Utils.isNull(this.error)) {
//            error = new Error();
//            error.setMessage(Messages.getMessage(message));
//            error.setType(ExceptionType.RuntimeException);
//        }
//        error.addRootCause(createRootCause(message));
//        return this;
//    }
//
//    public CommaException addError(String errorMessage) {
//        if(Utils.isNull(this.error)) {
//            error = new Error();
//            error.setMessage(errorMessage);
//            error.setType(ExceptionType.RuntimeException);
//        }
//        error.addRootCause(createRootCause(errorMessage));
//        return this;
//    }
//    private RootCause createRootCause(Message errorMessage) {
//        String message = Messages.getMessage(errorMessage);
//        return createRootCause(message);
//    }
//
//    private RootCause createRootCause(String errorMessage) {
//        RootCause rootCause = new RootCause();
//        rootCause.setReason(errorMessage);
//        return rootCause;
//    }
//
//    public CommaException addError(Message message, String errorType) {
//        if(Utils.isNull(this.error))
//            error = new Error();
//        error.addRootCause(createRootCause(message, errorType));
//        return this;
//    }
//
//    private RootCause createRootCause(Message errorMessage, String errorType) {
//        String message = Messages.getMessage(errorMessage);
//        return createRootCause(message, errorType);
//    }
//
//    private RootCause createRootCause(String errorMessage, String errorType) {
//        RootCause rootCause = new RootCause();
//        rootCause.setReason(errorMessage);
//        rootCause.setType(errorType);
//        return rootCause;
//    }
//}

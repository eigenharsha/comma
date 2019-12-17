package org.comma.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.MoreObjects;
import org.comma.exception.ErrorCode;
import org.comma.exception.core.enums.ExceptionType;
import org.comma.exception.core.message.Messages;
import org.comma.exception.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "type",
        "status",
        "code",
        "root_cause"
})
@JsonIgnoreProperties({"stackTrace", "backTrace"})
public class Error extends RuntimeException {

    @JsonProperty("message")
    private String message;
    @JsonProperty("type")
    private ExceptionType type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("code")
    private HttpStatus code;
    @JsonProperty("root_cause")
    private List<RootCause> rootCause = null;

    /**
     *
     */
    public Error(RuntimeException exception) {
        super(exception);
        this.message = exception.getMessage();
        this.type = ExceptionType.RuntimeException;
    }

    /**
     * No args constructor for use in serialization
     */
    public Error() {
        rootCause = new ArrayList<>();
    }

    /**
     * errorCode constructor
     *
     * @param errorCode
     * @param args
     */
    public Error(ErrorCode errorCode, @Nullable Object[] args) {
        this();
        if (errorCode != null) {
            this.message = Messages
                    .getMessage(errorCode.getCode(),
                            args);
        }

    }


    /**
     * errorCode constructor
     *
     * @param errorCode
     */
    public Error(ErrorCode errorCode) {
        this();
        if (errorCode != null) {
            this.message = Messages
                    .getMessage(errorCode.getCode(),
                            null);
        }
    }

    /**
     * @param rootCause
     * @param message
     * @param status
     * @param code
     * @param type
     */
    public Error(String message, String type, String status, HttpStatus code, List<RootCause> rootCause) {
        super();
        this.message = message;
        this.type = ExceptionType.RuntimeException;
        this.status = status;
        this.code = code;
        this.rootCause = rootCause;
    }

    public Error(String message, Throwable exception) {
        super(exception);
        this.message = message;
        this.type = ExceptionType.RuntimeException;
        this.rootCause = new ArrayList<>();
        rootCause.add(new RootCause()
                .withReason(exception.getMessage())
                .withDebugMessage(exception.getCause().toString()));
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public Error withMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("type")
    public ExceptionType getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(ExceptionType type) {
        this.type = type;
    }

    public Error withType(ExceptionType type) {
        this.type = type;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public Error withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("code")
    public HttpStatus getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public Error withCode(HttpStatus code) {
        this.code = code;
        return this;
    }

    @JsonProperty("root_cause")
    public List<RootCause> getRootCause() {
        return rootCause;
    }

    @JsonProperty("root_cause")
    public void setRootCause(List<RootCause> rootCause) {
        this.rootCause = rootCause;
    }

    public void addRootCause(RootCause rootCause) {
        this.rootCause.add(rootCause);
    }

    public Error withRootCause(List<RootCause> rootCause) {
        this.rootCause = rootCause;
        return this;
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper moreObjects = MoreObjects.toStringHelper(this);
        if (Utils.isNotNull(message)) moreObjects.add("message", message);
        if (Utils.isNotNull(type)) moreObjects.add("type", type);
        if (Utils.isNotNull(status)) moreObjects.add("status", status);
        if (Utils.isNotNull(code)) moreObjects.add("code", code);
        if (Utils.isNotNull(rootCause)) moreObjects.add("\nrootCause", rootCause);
        return moreObjects.toString();
    }
}
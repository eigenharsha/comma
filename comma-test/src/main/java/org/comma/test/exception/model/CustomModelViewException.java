package org.comma.test.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.comma.exception.core.message.LowerCasePropertyNameResolver;
import org.comma.exception.model.CommaException;
import org.comma.exception.rest.AbstractModelViewException;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonTypeIdResolver(LowerCasePropertyNameResolver.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error",
        "success",
        "status",
        "message"
})
public class CustomModelViewException extends AbstractModelViewException {

    @JsonProperty("success")
    public boolean success;

    @JsonProperty("status")
    public HttpStatus status;

    @JsonProperty("message")
    public String message;

    @JsonProperty("timestamp")
    public String timestamp;

    @JsonProperty("code")
    private int code;

    public CustomModelViewException() {
//        Config configBean = ApplicationContextProvider.getBeen(Config.class);
//        timestamp = DateTimeUtils.getFormatedDate(configBean.getDefaultDateTimeFormat());
//        this.error = new ArrayList<>();
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addError(CommaException exception) {
        this.error.add(exception);
    }

    public void setError(List<CommaException> exceptions) {
        this.error = exceptions;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.status = HttpStatus.valueOf(code);
    }
}

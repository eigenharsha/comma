package org.comma.exception.rest;

import org.comma.exception.model.CommaException;
import org.comma.exception.model.RootCause;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AbstractModelViewException implements ExceptionTemplate {
    @JsonProperty("success")
    public boolean success;

    @JsonProperty("status")
    public HttpStatus status;

    @JsonProperty("message")
    public String message;

    @JsonProperty("timestamp")
    public String timestamp;

    protected List<CommaException> error;

    @JsonProperty("error")
    protected List<RootCause> causes;

    @JsonProperty("code")
    private int code;

    public AbstractModelViewException() {
//        Config configBean = ApplicationContextProvider.getBeen(Config.class);
//        timestamp = DateTimeUtils.getFormatedDate(configBean.getDefaultDateTimeFormat());
        this.error = new ArrayList<>();
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addError(CommaException commaException) {
        this.error.add(commaException);
    }

    @Override
    public void addCause(CommaException ex) {
        this.error.add(ex);
    }

    public void setError(List<CommaException> commaExceptions) {
        this.error = commaExceptions;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }

    @Override
    public void addCause(java.lang.Exception ex) {
        this.error.add(new CommaException(ex));
    }

    @Override
    public void addError(java.lang.Exception ex) {
        this.error.add(new CommaException(ex));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.status = HttpStatus.valueOf(code);
    }

    public List<RootCause> getCauses() {
        return causes;
    }

    public void setCauses(List<RootCause> causes) {
        this.causes = causes;
    }
}

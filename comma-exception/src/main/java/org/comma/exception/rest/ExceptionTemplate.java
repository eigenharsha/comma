package org.comma.exception.rest;

import org.comma.exception.model.CommaException;
import org.springframework.http.HttpStatus;

public interface ExceptionTemplate {
    HttpStatus getStatus();
    void setStatus(HttpStatus status);
    void addCause(java.lang.Exception ex);
    void addError(java.lang.Exception ex);
    void addError(CommaException ex);
    void addCause(CommaException ex);
}

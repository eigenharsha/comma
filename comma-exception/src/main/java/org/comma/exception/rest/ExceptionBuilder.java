package org.comma.exception.rest;

import org.comma.exception.ExceptionHelper;
import org.comma.exception.core.builder.Builder;
import org.springframework.http.HttpStatus;

public interface ExceptionBuilder extends Builder<ExceptionTemplate> {
    ExceptionBuilder exception(Object ex) throws ExceptionHelper.CommonCommaException;
    ExceptionBuilder status(HttpStatus statusCode);
}

package org.comma.exception.rest;

import org.comma.exception.ExceptionHelper;
import org.comma.exception.model.CommaException;
import org.comma.exception.util.Utils;
import org.comma.exception.core.context.Comma;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class RestExceptionEventHandler extends ResponseEntityExceptionHandler {

    protected ExceptionBuilder exceptionBuilder;

    @ExceptionHandler(CommaException.class)
    public ResponseEntity<ExceptionTemplate> handleException(CommaException ex) throws ExceptionHelper.CommonCommaException {
        exceptionBuilder = Comma.getExceptionBuilder();
        exceptionBuilder = Utils.isNull(exceptionBuilder) ? new DefaultExceptionBuilder() : exceptionBuilder;
        try {
            ExceptionTemplate template = exceptionBuilder
                    .exception(ex)
                    .build();

            return ResponseEntity
                    .status(template.getStatus())
                    .body(template);
        } catch (ExceptionHelper.CommonCommaException e) {

            ExceptionTemplate template = exceptionBuilder
                    .exception(new java.lang.Exception("Internal Server Error"))
                    .build();

            return ResponseEntity
                    .status(template.getStatus())
                    .body(template);
        }
    }
}

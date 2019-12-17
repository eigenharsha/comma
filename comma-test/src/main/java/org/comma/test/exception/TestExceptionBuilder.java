package org.comma.test.exception;

import org.comma.exception.model.CommaException;
import org.comma.exception.model.HttpCommaException;
import org.comma.exception.model.RootCause;
import org.comma.exception.rest.ExceptionBuilder;
import org.comma.exception.rest.ExceptionTemplate;
import org.comma.exception.rest.ModelViewException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestExceptionBuilder implements ExceptionBuilder {
    CommaException commaException;
    private HttpStatus status;

    /**
     * Builds and returns the object.
     */
    @Override
    public ExceptionTemplate build() {
        ExceptionTemplate template = new ModelViewException();

        ((ModelViewException) template).setMessage(commaException.getMessage());
        ((ModelViewException) template).setSuccess(false);

        if (commaException instanceof HttpCommaException)
            ((ModelViewException) template).setStatus(((HttpCommaException) commaException).getStatus());
        else {
            ((ModelViewException) template).setStatus(HttpStatus.BAD_REQUEST);
        }

        List<CommaException> errors = commaException.getError();
        List<RootCause> causes = new ArrayList<>();
        for (CommaException ex : errors) {
            RootCause cause = new RootCause();
            cause.setReason(ex.getLocalizedMessage());
            cause.setType(ex.getType());
            causes.add(cause);
        }
        ((ModelViewException) template).setCauses(causes);
//        CommaException ex = new CommaException("inner error raised example");
//        template.addError(ex);
        return template;
    }

    private List<RootCause> BuildCause() {
        List<CommaException> causes = commaException.getError();
        List<RootCause> rootCauses = new ArrayList<>();
        for (CommaException ex : causes) {
            RootCause cause = new RootCause();
            cause.setReason(ex.getLocalizedMessage());
            cause.setType(ex.getType());
            rootCauses.add(cause);
        }
        return rootCauses;
    }

    @Override
    public ExceptionBuilder exception(Object ex) {
        try {
            this.commaException = (CommaException) ex;
        } catch (java.lang.Exception exception) {
            this.commaException = new CommaException(exception.getLocalizedMessage());
        }
        return this;
    }

    @Override
    public ExceptionBuilder status(HttpStatus statusCode) {
        this.status = statusCode;
        return this;
    }
}

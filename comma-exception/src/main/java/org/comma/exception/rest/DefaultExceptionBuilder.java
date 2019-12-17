package org.comma.exception.rest;

import org.comma.exception.ExceptionHelper;
import org.comma.exception.model.CommaException;
import org.comma.exception.model.HttpCommaException;
import org.comma.exception.model.RootCause;
import org.comma.exception.util.Utils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class DefaultExceptionBuilder implements ExceptionBuilder {

    private CommaException commaException;
    private HttpStatus status;
    private boolean isHttpStatusConfigured = false;

    public DefaultExceptionBuilder() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public DefaultExceptionBuilder(CommaException commaException) {
        this.commaException = commaException;
    }

    /**
     * push the coming commaException to build the rest template supported commaException
     *
     * @param commaException
     * @return
     */
    public DefaultExceptionBuilder exception(CommaException commaException) {
        this.commaException = commaException;
        return this;
    }

    /**
     * Builds and returns the object.
     */
    @Override
    public ExceptionTemplate build() {
        ExceptionTemplate template = new ModelViewException();

        ((ModelViewException) template).setMessage(commaException.getMessage());
        ((ModelViewException) template).setSuccess(false);
        ((ModelViewException) template).setStatus(status);

        if (!isHttpStatusConfigured) {
            if (commaException instanceof HttpCommaException)
                ((ModelViewException) template).setStatus(((HttpCommaException) commaException).getStatus());
            else {
                ((ModelViewException) template).setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        List<CommaException> causes = commaException.getError();
        if (Utils.isNullorEmpty(causes) &&
                !causes.isEmpty()) {
            ((ModelViewException) template).setError(causes);
        }

        return template;
    }

    private List<RootCause> BuildCause() {
        List<CommaException> causes = commaException.getError();
        List<RootCause> rootCauses = new ArrayList<>();
        for (CommaException ex : causes) {
            RootCause cause = new RootCause();
            cause.setReason(ex.getLocalizedMessage());
            rootCauses.add(cause);
        }
        return rootCauses;
    }

    @Override
    public ExceptionBuilder exception(Object ex) throws ExceptionHelper.CommonCommaException {
        try {
            this.commaException = (CommaException) ex;
        } catch (java.lang.Exception exception) {
            ExceptionHelper.propagate(exception);
        }
        return this;
    }

    @Override
    public ExceptionBuilder status(HttpStatus status) {
        this.status = status;
        isHttpStatusConfigured = true;
        return this;
    }
}

package org.comma.exception.rest;//package org.ebenso.comma.exception.rest;
//
//import CommaException;
//import HttpCommaException;
//import org.springframework.http.HttpStatus;
//
//public abstract class AbstractExceptionBuilder {
//
//    private CommaException exception;
//
//    public ExceptionTemplate build() {
//        if (exception instanceof HttpCommaException) {
//            return buildFromHttpException(exception);
//        } else if (exception instanceof CommaException) {
//            return buildFromException(exception);
//        } else
//            return null;
//    }
//
//    private ExceptionTemplate buildFromException(CommaException exception) {
//        ExceptionTemp template = new ExceptionTemp();
//        template.setMessage(exception.getMessage());
//        template.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        template.setSuccess(false);
//        template.setError(exception.getError());
//        return template;
//    }
//
//    private ExceptionTemplate buildFromHttpException(CommaException exception) {
//        HttpCommaException httpException = (HttpCommaException) exception;
//        ExceptionTemp template = buildFromException(exception);
//        template.setStatus(httpException.getStatus());
//        return template;
//    }
//
//    public abstract AbstractExceptionBuilder withTemplate(ModelViewException template);
//
//}

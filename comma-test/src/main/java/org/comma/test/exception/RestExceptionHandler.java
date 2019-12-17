package org.comma.test.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RestExceptionHandler extends org.comma.exception.rest.RestExceptionEventHandler {
//    @Autowired
//    TestExceptionBuilder builder;
//
//    @ExceptionHandler(CommaException.class)
//    public ResponseEntity<ExceptionTemplate> handleException(CommaException ex) {
//        ExceptionTemplate template = builder
//                .commaException(ex)
//                .build();
//
//        return ResponseEntity
//                .status(template.getStatus())
//                .body(template);
//    }
}

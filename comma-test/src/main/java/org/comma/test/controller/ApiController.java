package org.comma.test.controller;

import org.comma.exception.model.CommaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/comma-test")
    public ResponseEntity<String> getInfo() throws CommaException {
        CommaException ex = new CommaException("Null CommaException");
        ex.addCause("this is internal cause");
        throw ex;
    }

    @GetMapping
    public ResponseEntity getinformaiton() {
        return ResponseEntity.ok("comma without get path info");
    }
}

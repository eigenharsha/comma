package org.comma.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/error")
public class ErrorController {

    public ResponseEntity geterror() {
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request found");
    }
}

package org.comma.exception;

import org.springframework.http.HttpStatus;

public interface HttpException {

    HttpStatus status = HttpStatus.OK; // default

    HttpStatus getStatus();

    void setStatus(HttpStatus status);

}

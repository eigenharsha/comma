package org.comma.core.enums;

public enum  ExceptionType {

    RuntimeException("RuntimeException"),
    IndexOutOfBoundsException("IndexOutOfBoundsException");

    private final String type;

    ExceptionType(String exceptionType) {
        this.type = exceptionType;
    }
}

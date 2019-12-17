package org.comma.exception;

/**
 * CommaException Code for TA-Gateway
 ***/
public enum Message implements ErrorCode {

    ILLIGALE_STATE_ECEPTION,
    ERROR_WHILE_AUTHENTICATING_USER,
    ARGUMENTS_ARE_EMPTY_OR_NULL,
    PROTOCOL_IS_EMPTY_OR_NULL,
    INVALID_ARGUMENT_EXCEPTION,
    MISSING_REQUIRED_QUERY_PARAMETER,
    INVALID_URI,
    AUTHENTICATION_FAILED, AUTHERIZATION_FAILED;

    @Override
    public String getCode() {
        return name();
    }


}

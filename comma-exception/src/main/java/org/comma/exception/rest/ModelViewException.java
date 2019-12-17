package org.comma.exception.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.comma.exception.core.message.LowerCasePropertyNameResolver;

@JsonTypeIdResolver(LowerCasePropertyNameResolver.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "status",
        "message",
        "error"
})
public class ModelViewException extends AbstractModelViewException {
}
